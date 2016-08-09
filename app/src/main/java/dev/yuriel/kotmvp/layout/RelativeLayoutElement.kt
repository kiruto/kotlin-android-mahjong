package dev.yuriel.kotmvp.layout

/**
 * Created by yuriel on 8/10/16.
 */
class RelativeLayoutElement(override val id: String): LayoutElement() {
    infix fun alignTopOf(id: String) {
        val t = target(id)?: return
        attr alignTopOf t
    }

    private fun target(id: String): LayoutPosition? = this[id]?.attr
}