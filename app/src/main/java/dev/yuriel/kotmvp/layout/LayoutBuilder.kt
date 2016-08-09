package dev.yuriel.kotmvp.layout

/**
 * Created by yuriel on 8/9/16.
 */
fun layout(init: LayoutElement.() -> Unit): LayoutElement {
    val layout = RootScreen()
    layout.init()
    return layout
}

fun test() {

    layout {
        relative("id5") {

        }

        relative("id1") {
            44F o 93F
            23F x 99F
            alignTopOf("id5")
        }
    }

}
