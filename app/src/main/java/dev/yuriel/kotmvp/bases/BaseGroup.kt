/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmvp.bases

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.Group
import dev.yuriel.kotmvp.Dev

/**
 * Created by yuriel on 8/6/16.
 */
abstract class BaseGroup: Group() {
    private val circle: ShapeRenderer?

    abstract fun destroy()

    init {
        if (Dev.VIR_DEBUG) {
            circle = ShapeRenderer()
            debug = Dev.VIR_DEBUG
        } else {
            circle = null
        }
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        //drawOrigin()
    }

    fun drawOrigin() {
        if (!Dev.VIR_DEBUG) return
        circle?.color = Color.RED
        circle?.begin(ShapeRenderer.ShapeType.Filled)
        circle?.circle(originX, originY, 2F)
        circle?.end()
    }
}