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

package dev.yuriel.kotmvp.layout

/**
 * Created by yuriel on 8/9/16.
 */
class RootScreen: LayoutElement() {
    override var id = "screen"
        set(value) {
            children.remove(id)
            field = value
            children.put(value, this)
        }

    override val attr = getScreenLayout()

    var unit: Number
        set(value) {
            LayoutElement.unit = value
        }
        get() = LayoutElement.unit

    fun relative(id: String, init: RelativeLayoutElement.() -> Unit): RelativeLayoutElement {
        return layout(RelativeLayoutElement(id), init)
    }

    fun absolute(id: String, init: AbsoluteLayoutElement.() -> Unit): AbsoluteLayoutElement {
        return layout(AbsoluteLayoutElement(id), init)
    }

    companion object {
        fun layout(init: RootScreen.() -> Unit): RootScreen {
            val layout = RootScreen()
            layout.init()
            children.put(layout.id, layout)
            return layout
        }
    }
}