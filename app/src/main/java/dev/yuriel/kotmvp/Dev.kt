/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 yuriel
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *
 */

package dev.yuriel.kotmvp

import android.app.Activity
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import dev.yuriel.kotmvp.viewports.ExtendViewportImpl
import dev.yuriel.kotmvp.viewports.FillViewportImpl
import dev.yuriel.kotmvp.viewports.FitViewportImpl
import dev.yuriel.kotmvp.viewports.StretchViewportImpl

/**
 * Created by yuriel on 8/2/16.
 */
object Dev {
//    private val height: Int by lazy { Gdx.graphics.height }
//    private val width: Int by lazy { Gdx.graphics.width }

    const val VIR_DEBUG = true

    const val MAX_X = 640F
    const val MAX_Y = 360F

    const val NORMAL_WEIGHT = 4
    const val NORMAL_WIDTH = NORMAL_WEIGHT * MAX_X
    const val NORMAL_HEIGHT = NORMAL_WEIGHT * MAX_Y

    const val MIN_WEIGHT = 2
    const val MIN_WIDTH = MIN_WEIGHT * MAX_X
    const val MIN_HEIGHT = MIN_WEIGHT * MAX_Y

//    val DEFAULT_WIDTH = getDefaultWeight() * MAX_X
//    val DEFAULT_HEIGHT = getDefaultWeight() * MAX_Y

    private var width = getDefaultWidth()
    private var height = getDefaultHeight()

//    val UX: Float by lazy {
//        val width = if (width > height) width else height
//        width.toFloat() / MAX_X
//    }
//
//    val UY: Float by lazy {
//        val height = if (width < height) width else height
//        height.toFloat() / MAX_Y
//    }
    val UX: Float = getDefaultWeight().toFloat()
    val UY: Float = getDefaultWeight().toFloat()
    val U: Float = getDefaultWeight().toFloat()

    //val U: Float by lazy { (UX + UY) / 2F }

    val cam = OrthographicCamera(getDefaultWidth(), getDefaultHeight())
    val fillViewport = FillViewportImpl(cam)
    val fitViewport = FitViewportImpl(cam)
    val stretchViewport = StretchViewportImpl(cam)
    val extendViewport = ExtendViewportImpl(cam)

    var defaultViewport: Viewport = extendViewport

    var topActivity: Activity? = null
    var game: Game? = null
    var glApp: AndroidApplication? = null
        get() = topActivity as AndroidApplication
        private set

    fun setViewport(viewport: Viewport) {
        viewport.apply(true)
        viewport.update(Gdx.graphics.width, Gdx.graphics.height, true)
    }

    fun setDefaultViewport() {
        setViewport(defaultViewport)
    }

    fun getDefaultWeight(): Int {
        return MIN_WEIGHT
    }

    fun getDefaultWidth(): Float {
        return getDefaultWeight() * MAX_X
    }

    fun getDefaultHeight(): Float {
        return getDefaultWeight() * MAX_Y
    }
}