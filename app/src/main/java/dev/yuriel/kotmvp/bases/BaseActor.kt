package dev.yuriel.kotmvp.bases

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.Actor
import dev.yuriel.kotmvp.Dev

/**
 * Created by yuriel on 8/5/16.
 */
abstract class BaseActor: Actor(){
    private val circle: ShapeRenderer?

    init {
        debug = Dev.VIR_DEBUG
    }

    abstract fun destroy()
    abstract fun onDraw(batch: Batch?, parentAlpha: Float)

    init {
        if (Dev.VIR_DEBUG) {
            circle = ShapeRenderer()
        } else {
            circle = null
        }
    }

    final override fun draw(batch: Batch?, parentAlpha: Float) {
        onDraw(batch, parentAlpha)
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