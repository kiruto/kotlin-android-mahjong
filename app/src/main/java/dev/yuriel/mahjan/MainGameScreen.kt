/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.mahjan

import com.badlogic.gdx.graphics.FPSLogger
import dev.yuriel.kotmvp.Dev
import dev.yuriel.kotmvp.bases.BaseScreen
import dev.yuriel.mahjan.group.HandsGroup
import dev.yuriel.mahjan.interfaces.MainScreenPresenter
import dev.yuriel.mahjan.presenter.PlayPresenter
import dev.yuriel.mahjan.texture.TileMgr
import dev.yuriel.mahjan.texture.UITexture
import dev.yuriel.mahjan.view.MainGameRootViews

/**
 * Created by yuriel on 8/5/16.
 */
class MainGameScreen: BaseScreen(), MainScreenPresenter {

    private val fPSLogger = FPSLogger()
    val views = MainGameRootViews(this)
    val play = PlayPresenter(views)

    override fun preload() = listOf(TileMgr, UITexture)

    override fun show() {
        views.inject()
        //views.mockLayout4Test()
        play.addPlayer()
        //play.start()
        Dev.setViewport(Dev.stretchViewport)
    }

    override fun pause() {
        
    }

    override fun resize(width: Int, height: Int) {
        Dev.defaultViewport.update(width, height, true)
    }

    override fun hide() {
        
    }

    override fun render(delta: Float) {
        fPSLogger.log()
        views.clearScreen()
        drawGrid()
        views.render()
    }

    override fun resume() {
        
    }

    override fun dispose() {
        super.dispose()
        views.destroy()
    }

    override fun startRound() {
        play.start()
    }

    override fun getActionListener(): HandsGroup.Listener {
        return play
    }
}