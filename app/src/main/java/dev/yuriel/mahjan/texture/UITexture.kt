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

package dev.yuriel.mahjan.texture

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import dev.yuriel.kotmahjan.models.Hai

/**
 * Created by yuriel on 8/12/16.
 */
object UITexture: NormalTextureMgr("window.txt") {

    operator fun get(id: Int): TextureRegion? {
        return atlas?.findRegion("window_${id - 1}")
    }

    fun getBorder(): NinePatch {
        return NinePatch(this[2], this[3], this[6], this[7], this[15], this[10], this[19], this[20], this[22])
    }

    fun getSelect(): NinePatch {
        return NinePatch(this[24], 3, 3, 3, 3)
    }

    fun getBg(): TextureRegion {
        return this[1]!!
    }

    fun getBgLining(): TextureRegion {
        return this[23]!!
    }

    fun getArrowAnim(): Animation {
        val result = Animation(1F, this[25], this[26], this[27], this[28])
        result.playMode = Animation.PlayMode.LOOP_PINGPONG
        return result
    }
}