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

import dev.yuriel.kotmvp.Dev
import dev.yuriel.kotmvp.FURO_TILE_HEIGHT
import dev.yuriel.kotmvp.bases.BaseGroup
import dev.yuriel.mahjan.actor.SingleTileActor
import dev.yuriel.mahjan.enums.TileStatus
import dev.yuriel.mahjan.model.TileWrapper

/**
 * Created by yuriel on 8/9/16.
 */
class TileHorizontalGroup(tiles: List<TileWrapper>, val position: Int): BaseGroup() {

    private val tileList: MutableList<SingleTileActor> = mutableListOf()
    val size: Int
        get() = tileList.size

    init {
        update(tiles)
    }

    fun update(tiles: List<TileWrapper>) {
        tileList.clear()
        for (t in tiles) {
            val actor = SingleTileActor(t)
            tileList.add(actor)
            addActor(actor)
        }
        calculate()
    }

    fun append(tile: TileWrapper) {
        val actor = SingleTileActor(tile)
        tileList.add(actor)
        addActor(actor)
        calculate()
    }

    fun calculate() {
        var originX = 0F
        for (t in tileList) {
            //t.setTilePosition(originX, 0F)
            t.position = originX
            when (t.status) {
                TileStatus.NORMAL,
                TileStatus.OBVERSE -> originX += t.width
                TileStatus.HORIZONTAL -> originX += t.height
            }
        }
        width = originX
        height = FURO_TILE_HEIGHT * Dev.U
    }

    override fun destroy() {
        tileList.clear()
    }
}