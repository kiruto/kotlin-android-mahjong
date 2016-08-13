/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
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