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

import android.graphics.YuvImage
import com.badlogic.gdx.scenes.scene2d.Actor
import dev.yuriel.kotmvp.LayoutWrongException
import java.util.*

/**
 * Created by yuriel on 8/10/16.
 */
abstract class LayoutElement {
    companion object {
        val children = mutableMapOf<String, LayoutElement>()
        var unit: Number = 1
    }

    abstract val id: String
    protected val attributes = hashMapOf<String, String>()
    open val attr = LayoutPosition(0F, 0F, 0F, 0F)
    open var actor: Actor? = null
        set(value) {
            field = value
            if (attr.size.width == 0F && attr.size.height == 0F) {
                attr.size.width = value?.width?: 0F
                attr.size.height = value?.height?: 0F
            }
        }

    var top: Number? = null
        get() = attr.top()
        private set
    var right: Number? = null
        get() = attr.right()
        private set
    var bottom: Number? = null
        get() = attr.bottom()
        private set
    var left: Number? = null
        get() = attr.left()
        private set
    var width: Number? = null
        get() = attr.size.width
        set(value) {
            field = value?.toFloat()?: 0L * unit.toFloat()
            setW(value?: field?: 0)
        }
    var height: Number? = null
        get() = attr.size.height
        set(value) {
            field = value?.toFloat()?: 0L * unit.toFloat()
            setH(value?: field?: 0)
        }

    fun actor(init: Actor.() -> Unit) {
        actor?.init()
    }

    private fun setW(width: Number) {
        attr.size.width = width.toFloat() * unit.toFloat()
    }

    private fun setH(height: Number) {
        attr.size.height = height.toFloat() * unit.toFloat()
    }

    protected operator fun get(name: String): LayoutElement? = children[name]

    protected fun <T: LayoutElement> layout(layout: T, init: T.() -> Unit): T {
        if (children.containsKey(layout.id)) {
            throw LayoutWrongException()
        }
        layout.init()
        children.put(layout.id, layout)
        layout.actor?.setPosition(layout.attr.ghostOrigin.first!!, layout.attr.ghostOrigin.second!!)
        layout.actor?.setSize(layout.attr.size.width, layout.attr.size.height)
        return layout
    }
}