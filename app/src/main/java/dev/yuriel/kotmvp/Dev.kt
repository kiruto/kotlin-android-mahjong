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

    val UX: Float by lazy {
        val width = if (width < height) width else height
        width.toFloat() / 640
    }

    val UY: Float by lazy {
        val height = if (width < height) height else width
        height.toFloat() / 360
    }

    var topActivity: Activity? = null
    var game: Game? = null
    var glApp: AndroidApplication? = null
        get() = topActivity as AndroidApplication
        private set
}