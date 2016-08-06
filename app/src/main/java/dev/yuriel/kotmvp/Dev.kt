package dev.yuriel.kotmvp

import android.app.Activity
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.backends.android.AndroidApplication

/**
 * Created by yuriel on 8/2/16.
 */
object Dev {
    private val height: Int by lazy { Gdx.graphics.height }
    private val width: Int by lazy { Gdx.graphics.width }

    const val MAX_X = 640F
    const val MAX_Y = 360F

    val UX: Float by lazy {
        val width = if (width > height) width else height
        width.toFloat() / MAX_X
    }

    val UY: Float by lazy {
        val height = if (width < height) width else height
        height.toFloat() / MAX_Y
    }

    val U: Float by lazy { (UX + UY) / 2F }

    var topActivity: Activity? = null
    var game: Game? = null
    var glApp: AndroidApplication? = null
        get() = topActivity as AndroidApplication
        private set
}