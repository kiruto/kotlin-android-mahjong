/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmvp.bases

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import dev.yuriel.kotmvp.Dev
import dev.yuriel.mahjan.texture.TextureMgr
import dev.yuriel.kotmvp.view.GridLayerViews

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