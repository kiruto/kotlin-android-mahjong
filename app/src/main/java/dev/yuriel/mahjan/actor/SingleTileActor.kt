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

package dev.yuriel.mahjan.actor

import com.badlogic.gdx.graphics.g2d.Batch
import dev.yuriel.kotmvp.Dev
import dev.yuriel.kotmvp.FURO_TILE_HEIGHT
import dev.yuriel.kotmvp.FURO_TILE_WIDTH
import dev.yuriel.kotmvp.bases.BaseActor
import dev.yuriel.mahjan.enums.TileStatus
import dev.yuriel.mahjan.model.TileWrapper

/**
 * Created by yuriel on 8/9/16.
 */
class SingleTileActor(private val tile: TileWrapper): BaseActor() {

    private val size = Pair(FURO_TILE_WIDTH * Dev.U, FURO_TILE_HEIGHT * Dev.U)

    init {
        width = size.first
        height = size.second
    }

    val status = tile.status
    var position: Float = 0F

    override fun destroy() {
        tile.destroy()
    }

    override fun onDraw(batch: Batch?, parentAlpha: Float) {
        if (tile.status == TileStatus.OBVERSE) {
            batch?.draw(tile.back, position, 0F, size.first, size.second)
            return
        } else {
            batch?.draw(tile.texture, position, 0F, size.first, size.second)
        }
        if (tile.status == TileStatus.HORIZONTAL) {
            rotation = 90F
        }
    }
}