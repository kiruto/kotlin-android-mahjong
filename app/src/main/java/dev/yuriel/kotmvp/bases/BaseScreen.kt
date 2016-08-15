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

package dev.yuriel.kotmvp.bases

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import dev.yuriel.kotmvp.Dev
import dev.yuriel.mahjan.texture.TextureMgr
import dev.yuriel.kotmvp.views.GridLayerViews

/**
 * Created by yuriel on 8/3/16.
 */
abstract class BaseScreen: Screen {
    private val grid: GridLayerViews = GridLayerViews()
    //private val batch = SpriteBatch()
    private val font = BitmapFont()
    protected val TAG: String = javaClass.simpleName

    protected fun drawGrid() {
        //batch.begin()
        //font.draw(batch, TAG, 5 * Dev.UX, 5 * Dev.UY)
        grid.draw()
        //batch.end()
    }

    override fun dispose() {
        for (l in preload()?: return) {
            l.destroy()
        }
    }

    abstract fun preload(): List<TextureMgr>?
}