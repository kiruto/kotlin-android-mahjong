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

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import dev.yuriel.kotmahjan.models.DoraCannotFoundException
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmvp.Dev
import dev.yuriel.kotmvp.TILE_HEIGHT
import dev.yuriel.kotmvp.TILE_WIDTH
import dev.yuriel.kotmvp.bases.BaseActor
import dev.yuriel.mahjan.model.TileWrapper

/**
 * Created by yuriel on 8/5/16.
 */
class TilePlaceHolderActor(private var position: Int, isTsumo: Boolean = false): TileActor(isTsumo){
    override fun getTileSize() = Pair(TILE_WIDTH * Dev.UX, TILE_HEIGHT * Dev.UY)
    override fun getTileOrigin() = Pair(width * (position + getOffset()), 0F)
    override fun getTilePosition(): Int = position
    override fun setTilePosition(value: Int) {
        position = value
    }

    override fun onDraw(batch: Batch?, parentAlpha: Float) {
        if (null == texture) return
        batch?.draw(back, originX, originY + TILE_HEIGHT * Dev.UX - 0.5F * Dev.UX
                , TILE_WIDTH * Dev.UX, TILE_HEIGHT * Dev.UX * 0.3F)
        batch?.draw(texture, originX, originY, TILE_WIDTH * Dev.UX, TILE_HEIGHT * Dev.UX)
    }

    private fun getOffset(): Float {
        return if (isTsumo) 0.5F else 0F
    }
}