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

    companion object {
        fun layout(init: RootScreen.() -> Unit): RootScreen {
            val layout = RootScreen()
            layout.init()
            children.put(layout.id, layout)
            return layout
        }
    }
}