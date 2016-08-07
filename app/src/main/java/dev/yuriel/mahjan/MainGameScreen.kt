package dev.yuriel.mahjan

import dev.yuriel.kotmvp.Dev
import dev.yuriel.kotmvp.bases.BaseScreen
import dev.yuriel.mahjan.texture.TileMgr
import dev.yuriel.mahjan.views.MainGameRootViews
import java.util.*

/**
 * Created by yuriel on 8/5/16.
 */
class MainGameScreen: BaseScreen() {

    val views = MainGameRootViews()

    override fun preload() = listOf(TileMgr)

    override fun show() {
        views.inject()
        views.mockLayout4Test()
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