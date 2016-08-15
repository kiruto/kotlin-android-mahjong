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

package dev.yuriel.kotmvp.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import dev.yuriel.kotmvp.Dev
import dev.yuriel.kotmvp.bases.BaseScreen
import dev.yuriel.mahjan.texture.TextureMgr
import javax.microedition.khronos.opengles.GL10

/**
 * Created by yuriel on 8/2/16.
 */
class TitleScreen: BaseScreen() {

    private val batch = SpriteBatch()
    private val bf = BitmapFont()

    override fun preload() = null

    override fun show() {

    }

    override fun pause() {

    }

    override fun resize(width: Int, height: Int) {

    }

    override fun hide() {

    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT)
        Gdx.gl.glClearColor(10F, 10F, 10F, 10F)
        batch.begin()
        bf.draw(batch, "now loading", 600 * Dev.UX, 30 * Dev.UY)
        batch.end()
    }

    override fun resume() {

    }

    override fun dispose() {

    }
}