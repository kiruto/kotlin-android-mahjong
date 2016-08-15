/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 yuriel
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *
 */

package dev.yuriel.mahjan.group

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmvp.bases.BaseGroup
import dev.yuriel.mahjan.actor.TileActor
import dev.yuriel.mahjan.texture.TileMgr

/**
 * Created by yuriel on 8/6/16.
 */
abstract class TileGroup<out ACTOR: TileActor>: BaseGroup() {
    protected val tileList: Array<out TileActor>
    protected val tsumo: TileActor
    abstract fun getOrigin(): Pair<Float, Float>
    protected abstract fun factory(position: Int, isTsumo: Boolean = false): ACTOR
    var size: Int = 0

    init {
        TileMgr.load()
        tileList = Array<TileActor>(13) { i ->
            val actor = factory(position = i)
            actor.resetPosition()
            addActor(actor)
            actor
        }
        tsumo = factory(14, true)
        addActor(tsumo)
    }

    fun updateList(haiList: List<Hai>) {
        if (haiList.size < size) {
            for (actor in tileList) {
                if (!haiList.contains(actor.tile?.hai)) {
                    actor.update(null)
                }
            }
        } else {
            for (index in 0..tileList.size - 1) {
                val actor = tileList[index]
                if (haiList.size - 1 >= index) {
                    actor.update(haiList[index])
                } else {
                    actor.update(null)
                }
            }
        }
        size = haiList.size
    }

    fun updateTsumo(hai: Hai?) {
        tsumo.setTilePosition(size)
        tsumo.update(hai)
    }

    override fun destroy() {
        for (i in tileList) {
            i.destroy()
        }
    }
}