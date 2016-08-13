package dev.yuriel.mahjan

import dev.yuriel.kotmvp.Dev
import dev.yuriel.kotmvp.bases.BaseScreen
import dev.yuriel.mahjan.presenter.PlayPresenter
import dev.yuriel.mahjan.texture.TileMgr
import dev.yuriel.mahjan.texture.UITexture
import dev.yuriel.mahjan.views.MainGameRootViews

/**
 * Created by yuriel on 8/5/16.
 */
class MainGameScreen: BaseScreen() {

    val views = MainGameRootViews()
    val play = PlayPresenter(views)

    override fun preload() = listOf(TileMgr, UITexture)

    override fun show() {
        views.inject()
        //views.mockLayout4Test()
        play.addPlayer()
        play.start()
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
}