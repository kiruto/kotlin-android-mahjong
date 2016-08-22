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

package dev.yuriel.kotmvp.views

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Label
import dev.yuriel.kotmvp.Dev

/**
 * Created by yuriel on 8/3/16.
 */
class GridLayerViews(val weight: Int = 4) {
    private val sr = ShapeRenderer()
    private val labels: MutableList<Label> = mutableListOf()

    fun draw() {
        Gdx.gl.glLineWidth(1F)
        sr.setAutoShapeType(true)
        sr.begin()
        sr.setColor(255F, 255F, 255F, 0.1F)
        val hw = 40 * Dev.UY / weight
        val ww = 40 * Dev.UX / weight
        for (w in 0..16 * weight) {
            val start = Vector2(0F, w * hw)
            val end = Vector2(640F * Dev.UX, w * hw)
            sr.line(start, end)
        }
        for (h in 0..9 * weight * 2) {
            val start = Vector2(h * ww, 0F)
            val end = Vector2(h * ww, 360F * Dev.UY)
            sr.line(start, end)
        }
        sr.end()
    }
}