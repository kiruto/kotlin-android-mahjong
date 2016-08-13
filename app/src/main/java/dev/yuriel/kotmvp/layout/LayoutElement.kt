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
    var actor: Actor? = null

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