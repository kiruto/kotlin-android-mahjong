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

package dev.yuriel.kotmvp.layout

/**
 * Created by yuriel on 8/11/16.
 */
class AbsoluteLayoutElement(override val id: String): LayoutElement() {

    fun rect(top: Number, right: Number, bottom: Number, left: Number) {
        attr.size.width = right.toFloat() - left.toFloat()
        attr.size.height = top.toFloat() - bottom.toFloat()
        attr.origin.x = left.toFloat()
        attr.origin.y = bottom.toFloat()
    }

    fun String.top() = target(this)?.top()?: 0F
    fun String.right() = target(this)?.right()?: 0F
    fun String.bottom() = target(this)?.bottom()?: 0F
    fun String.left() = target(this)?.left()?: 0F

    fun moveUnits(relativeX: Number?, relativeY: Number?) {
        attr.correct((relativeX?.toFloat()?: 0F) * unit.toFloat(),
                (relativeY?.toFloat()?: 0F) * unit.toFloat())
    }

    private fun target(id: String): LayoutPosition? = this[id]?.attr
}