/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmvp.bases

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import dev.yuriel.kotmvp.Dev

/**
 * Created by yuriel on 8/5/16.
 */
abstract class BaseStage: Stage(Dev.defaultViewport) {

    init {
        setDebugAll(Dev.VIR_DEBUG)
        setDebugInvisible(!Dev.VIR_DEBUG)
    }
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