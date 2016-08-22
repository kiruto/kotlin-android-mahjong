/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 yuriel<yuriel3183@gmail.com>
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
 * SOFTWARE.
 */

package dev.yuriel.mahjan.group

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmvp.Dev
import dev.yuriel.mahjan.actor.TilePlaceHolderActor
import rx.Observable

/**
 * Created by yuriel on 8/5/16.
 */
class HandsGroup: TileGroup<TilePlaceHolderActor>() {

    private val actionListener = mutableListOf<Listener>()

    override fun getOrigin(): Pair<Float, Float> = Pair(50F * Dev.UX, 0F)
    override fun factory(position: Int, isTsumo: Boolean): TilePlaceHolderActor {
        val actor = TilePlaceHolderActor(position, isTsumo)
        val listener = object: ClickListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                //println("touch down: ${actor.tile?.hai.toString()}")
                for (l in actionListener) {
                    l.onTileTouchDown(actor.tile?.hai?: break)
                }
                return super.touchDown(event, x, y, pointer, button)
            }

            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                //println("touch up: ${actor.tile?.hai.toString()}")
                super.touchUp(event, x, y, pointer, button)
                for (l in actionListener) {
                    l.onTileTouchUp(actor.tile?.hai?: break)
                }
            }
        }
        actor.addListener(listener)
        return actor
    }

    fun addOnActionListener(l: Listener) {
        actionListener.add(l)
    }

    interface Listener {
        fun onTileTouchDown(hai: Hai)
        fun onTileTouchUp(hai: Hai)
    }
}
