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
        protected var magnification: Number = 1
    }

    protected val attributes = hashMapOf<String, String>()
    open val attr = LayoutPosition(0F, 0F, 0F, 0F)
    var actor: Actor? = null
    var unit: Number
        set(value) {
            magnification = value
        }
        get() = magnification

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
            w(value?: field?: 0)
        }
    var height: Number? = null
        get() = attr.size.height
        set(value) {
            field = value?.toFloat()?: 0L * unit.toFloat()
            w(value?: field?: 0)
        }

    abstract val id: String

    fun relative(id: String, init: RelativeLayoutElement.() -> Unit): RelativeLayoutElement {
        return layout(RelativeLayoutElement(id), init)
    }

    fun move(relativeX: Number?, relativeY: Number?) {
        attr.correct((relativeX?.toFloat()?: 0F) * unit.toFloat(),
                (relativeY?.toFloat()?: 0F) * unit.toFloat())
    }

    fun moveBy(relativeX: Number?, relativeY: Number?) {
        attr.correct(relativeX?.toFloat()?: 0F, relativeY?.toFloat()?: 0F)
    }

    infix fun x(originX: Number) {
        attr.origin.x = originX.toFloat() * unit.toFloat()
    }

    infix fun y(originY: Number) {
        attr.origin.y = originY.toFloat() * unit.toFloat()
    }

    fun o(x: Number?, y: Number?) {
        x(x?: 0)
        y(y?: 0)
    }

    infix fun w(width: Number) {
        attr.size.width = width.toFloat() * unit.toFloat()
    }

    infix fun h(height: Number) {
        attr.size.height = height.toFloat() * unit.toFloat()
    }

    infix fun Number.x(other: Number) {
        w(this)
        h(other)
    }

    protected operator fun get(name: String): LayoutElement? = children[name]

    protected fun <T: LayoutElement> layout(layout: T, init: T.() -> Unit): T {
        if (children.containsKey(layout.id)) {
            throw LayoutWrongException()
        }
        layout.init()
        children.put(layout.id, layout)
        layout.actor?.setPosition(layout.attr.origin.x, layout.attr.origin.y)
        layout.actor?.setSize(layout.attr.size.width, layout.attr.size.height)
        return layout
    }
}