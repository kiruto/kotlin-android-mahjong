package dev.yuriel.mahjan

import com.badlogic.gdx.Gdx.*
import com.badlogic.gdx.graphics.GL20
import dev.yuriel.kotmahjan.ctrl.HaiMgr
import dev.yuriel.kotmvp.bases.BaseScreen
import dev.yuriel.mahjan.group.HandsGroup
import dev.yuriel.mahjan.group.LeftSideGroup
import dev.yuriel.mahjan.group.RightSideGroup
import dev.yuriel.mahjan.stage.ViewStage
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
    }

    override fun pause() {
        
    }

    override fun resize(width: Int, height: Int) {
        
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