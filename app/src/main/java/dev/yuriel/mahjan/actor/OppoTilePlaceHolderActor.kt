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
import dev.yuriel.kotmvp.SMALL_TILE_HEIGHT
import dev.yuriel.kotmvp.SMALL_TILE_WIDTH

/**
 * Created by yuriel on 8/7/16.
 */
class OppoTilePlaceHolderActor(private var position: Int, isTsumo: Boolean = false): TileActor(isTsumo) {
    override fun getTileSize() = Pair(SMALL_TILE_WIDTH * Dev.U, SMALL_TILE_HEIGHT * Dev.U)
    override fun getTileOrigin() = Pair(- (position + getOffset()) * width, 0F)
    override fun getTilePosition(): Int = position
    override fun setTilePosition(value: Int) {
        position = value
    }

    override fun onDraw(batch: Batch?, parentAlpha: Float) {
        if (null == obverse) return
        batch?.draw(obverse, originX, originY, width, height)
    }

    private fun getOffset(): Float {
        return if (isTsumo) 0.5F else 0F
    }
}