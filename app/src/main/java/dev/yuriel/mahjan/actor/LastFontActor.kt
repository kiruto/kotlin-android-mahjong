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

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import dev.yuriel.kotmvp.bases.BaseActor
import dev.yuriel.mahjan.texture.NormalFontBlock

/**
 * Created by yuriel on 8/18/16.
 */
class LastFontActor: BaseActor() {

    var font: NormalFontBlock?
        private set

    var text: String = ""
        set(value) {
            field = value
            font?.painter?.text(field)
            width = font!!.layout.width
            height = font!!.layout.height
        }

    init {
        font = NormalFontBlock()
        font!!.load("last.fnt", "last.png")
                .color { i -> Color(255F, 255F, 255F, color.a) }
                //.scale { i -> Pair(scaleX, scaleY) }
                //.origin { i -> font!!.getCenterOriginToText(x, y, width, height) }
    }

    fun update(num: Int) {
        text = "残り $num"
    }

    override fun destroy() {
        font = null
    }

    override fun onDraw(batch: Batch?, parentAlpha: Float) {
        if (null != batch) font?.draw(batch) else font?.draw()
    }
}