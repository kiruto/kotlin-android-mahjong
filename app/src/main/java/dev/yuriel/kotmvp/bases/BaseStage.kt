package dev.yuriel.kotmvp.bases

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage

/**
 * Created by yuriel on 8/5/16.
 */
abstract class BaseStage: Stage() {
    var active = false
        set(value) {
            field = value
            if (value)
                Gdx.input.inputProcessor = this
        }

    fun render() {
        act()
        draw()
    }

    final override fun dispose() {
        super.dispose()
        destroy()
    }

    abstract fun destroy()
}