package dev.yuriel.kotmvp.layout

import android.graphics.YuvImage
import dev.yuriel.kotmvp.LayoutWrongException
import java.util.*

/**
 * Created by yuriel on 8/10/16.
 */
abstract class LayoutElement {
    protected val children = mutableMapOf<String, LayoutElement>()
    protected val attributes = hashMapOf<String, String>()
    val attr = LayoutPosition(0F, 0F, 0F, 0F)

    abstract val id: String

    fun relative(id: String, init: RelativeLayoutElement.() -> Unit): RelativeLayoutElement {
        return layout(RelativeLayoutElement(id), init)
    }

    infix fun x(originX: Float) {
        attr.origin.x = originX
    }

    infix fun y(originY: Float) {
        attr.origin.y = originY
    }

    fun origin(x: Float, y: Float) {
        x(x)
        y(y)
    }

    infix fun w(width: Float) {
        attr.size.width = width
    }

    infix fun h(height: Float) {
        attr.size.height = height
    }

    fun size(width: Float, height: Float) {
        w(width)
        h(height)
    }

    infix fun Float.o(other: Float) {
        x(this)
        y(other)
    }

    infix fun Float.x(other: Float) {
        w(this)
        h(other)
    }

    protected operator fun get(name: String): LayoutElement? = children[name]

    protected fun <T: LayoutElement> layout(layout: T, init: T.() -> Unit): T {
        layout.init()
        if (children.containsKey(layout.id)) {
            throw LayoutWrongException()
        }

        children.put(layout.id, layout)
        return layout
    }
}