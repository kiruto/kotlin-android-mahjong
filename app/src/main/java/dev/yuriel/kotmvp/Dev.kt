package dev.yuriel.kotmvp

import com.badlogic.gdx.Gdx

/**
 * Created by yuriel on 8/2/16.
 */
object Dev {
    private val height: Int by lazy { Gdx.graphics.height }
    private val width: Int by lazy { Gdx.graphics.width }

    val UX: Double by lazy {
        val width = if (width < height) width else height
        width.toDouble() / 360
    }

    val UY: Double by lazy {
        val height = if (width < height) height else width
        height.toDouble() / 640
    }
}