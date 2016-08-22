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

package dev.yuriel.mahjan.model

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.mahjan.enums.TileStatus
import dev.yuriel.mahjan.texture.TileMgr

/**
 * Created by yuriel on 8/5/16.
 */
class TileWrapper() {

    constructor(hai: Hai): this() {
        this.hai = hai
    }

    var texture: TextureRegion? = null
        private set

    var back: TextureRegion? = null
        private set

    var obverse: TextureRegion? = null
        private set

    var display: Int = 0
        set(value) {
            field = field and value
        }
    var hai: Hai? = null
        set(value) {
            field = value
            texture = initTexture(hai = value)
        }
    var status: TileStatus = TileStatus.NORMAL
        set(value) {
            field = value
            texture = initTexture(status = value)
        }
    var selected: Boolean = false

    fun destroy() {
        hai = null
    }

    private fun initTexture(hai: Hai? = this.hai, status: TileStatus = this.status): TextureRegion? {
        if (null == hai) {
            back = null
            obverse = null
            return null
        } else {
            TileMgr.load()
            back = TileMgr.getBack()
            obverse = TileMgr.getObverse()
            return TileMgr[hai]
        }
    }
}