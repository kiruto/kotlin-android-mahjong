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

package dev.yuriel.mahjan.texture

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.HaiType
import dev.yuriel.kotmahjan.models.UnbelievableException

/**
 * Created by yuriel on 8/5/16.
 */
object TileMgr: NormalTextureMgr("tiles.txt") {

    operator fun get(hai: Hai): TextureRegion? {
        val name = findTextureNameByHai(hai)
        return this[name]
    }

    fun getBack(): TextureRegion? {
        return this["back"]
    }

    fun getObverse(): TextureRegion? {
        return this["back_side"]
    }

    private fun findTextureNameByHai(hai: Hai): String {
        fun normal(hai: Hai): String {
            val fileName: String
            val haiName = hai.toString()
            when (haiName.length) {
                2 -> fileName = haiName
                3 -> {
                    when(haiName.first()) {
                        'p' -> fileName = "aka1"
                        's' -> fileName = "aka2"
                        'm' -> fileName = "aka3"
                        else -> throw UnbelievableException()
                    }
                }
                else -> throw UnbelievableException()
            }
            return fileName
        }
        return when(hai.type) {
            HaiType.E -> "ji1"
            HaiType.S -> "ji2"
            HaiType.W -> "ji3"
            HaiType.N -> "ji4"
            HaiType.D -> "ji5"
            HaiType.H -> "ji6"
            HaiType.T -> "ji7"
            else -> normal(hai)
        }
    }
}