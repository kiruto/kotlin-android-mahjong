/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 yuriel<yuriel3183@gmail.com>
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
 * SOFTWARE.
 */

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