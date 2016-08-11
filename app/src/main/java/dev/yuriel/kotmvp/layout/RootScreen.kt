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