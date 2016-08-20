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
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
 * Created by yuriel on 8/12/16.
 */
open class AnimatedFontBlock(private var batch: Batch? = null) {

    var font: BitmapFont? = null
        private set
    var painter: Painter? = null
        private set

    var width: Float = 0F
    var height: Float = 0F

    val layout: GlyphLayout = GlyphLayout()

    fun load(fileName: String, imageName: String): Painter {
        font = BitmapFont(Gdx.files.internal(fileName), Gdx.files.internal(imageName), false)
        if (null == batch) {
            batch = SpriteBatch()
        }
        painter = Painter()
        layout.setText(font, "")
        return painter!!
    }

    fun draw() {
        painter?.draw()
    }

    fun draw(batch: Batch) {
        painter?.draw(batch)
    }

    fun getCenterOriginToText(x: Float, y: Float, width: Float, height: Float): Pair<Float, Float> {
        var resultX = x
        var resultY = y
        resultX += (width - this.width) / 2F
        resultY -= (height - this.height) / 2F
        return Pair(resultX, resultY)
    }

    inner class Painter internal constructor(){

        private var color: Color = Color(0F, 0F, 0F, 0F)
        private var x: Float = 0F
        private var y: Float = 0F
        private var scaleX: Float = 1F
        private var scaleY: Float = 1F
        private var text: String = ""
        private var frame: Int = 0
        private var colorGetter: ((Int) -> Color)? = null
        private var scaleGetter: ((Int) -> Pair<Float, Float>)? = null
        private var originGetter: ((Int) -> Pair<Float, Float>)? = null

        fun color(c: Color): Painter {
            color = c
            return this
        }

        fun color(r: Float, g: Float, b: Float, a: Float): Painter {
            color = Color(r, g, b, a)
            return this
        }

        fun color(f: (i: Int) -> Color): Painter {
            colorGetter = f
            return this
        }

        fun scale(x: Float, y: Float): Painter {
            scaleX = x
            scaleY = y
            return this
        }

        fun scale(f: (i: Int) -> Pair<Float, Float>): Painter {
            scaleGetter = f
            return this
        }

        fun text(t: String): Painter {
            text = t
            layout.setText(font, text)
            width = layout.width
            height =layout.height
            return this
        }

        fun origin(x: Float, y: Float): Painter {
            this.x = x
            this.y = y
            return this
        }

        fun origin(f: (i: Int) -> Pair<Float, Float>): Painter {
            originGetter = f
            return this
        }

        internal fun draw() {
            batch?.begin()
            draw(batch)
            batch?.end()
        }

        internal fun draw(batch: Batch?) {
            if (frame == Int.MAX_VALUE) frame = 0
            font?.color = colorGetter?.invoke(frame)?: color
            val scale: Pair<Float, Float>? = scaleGetter?.invoke(frame)
            font?.data?.scaleX = scale?.first?: scaleX
            font?.data?.scaleY = scale?.second?: scaleY
            if (null != scale) {
                width = layout.width * scale.first
                height = layout.height * scale.second
            }
            val origin: Pair<Float, Float>? = originGetter?.invoke(frame)
            x = origin?.first?: x
            y = origin?.second?: y
            font?.draw(batch, text, x, y)
            frame ++
        }
    }
}