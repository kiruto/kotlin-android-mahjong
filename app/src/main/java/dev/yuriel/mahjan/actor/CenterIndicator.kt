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
import com.badlogic.gdx.graphics.g2d.TextureRegion
import dev.yuriel.kotmvp.Dev
import dev.yuriel.kotmvp.FURO_TILE_WIDTH
import dev.yuriel.kotmvp.bases.BaseActor
import dev.yuriel.mahjan.texture.UITexture

/**
 * Created by yuriel on 8/11/16.
 */
class CenterIndicator: BaseActor() {

    private val texture: TextureRegion?
    private val size = Pair(FURO_TILE_WIDTH * 6F, FURO_TILE_WIDTH * 2F)

    init {
        UITexture.load()
        texture = UITexture[38]
        setSize(size.first * Dev.U, size.second * Dev.U)
    }

    override fun destroy() {

    }

    override fun onDraw(batch: Batch?, parentAlpha: Float) {
        batch?.draw(texture, x, y, size.first * Dev.U, size.second * Dev.U)
    }
}