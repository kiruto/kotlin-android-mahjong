package dev.yuriel.mahjan

import com.badlogic.gdx.Gdx.*
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import dev.yuriel.kotmahjan.ctrl.HaiMgr
import dev.yuriel.kotmvp.bases.BaseScreen
import dev.yuriel.mahjan.stage.HandsStage
import dev.yuriel.mahjan.texture.TextureMgr
import dev.yuriel.mahjan.texture.TileMgr

/**
 * Created by yuriel on 8/5/16.
 */
class MainGameScreen: BaseScreen() {

    val handStage = HandsStage()
    val haiList = HaiMgr().getTehai4Test()

    override fun preload() = listOf(TileMgr)

    override fun show() {
        handStage.updateList(haiList)
    }

    override fun pause() {
        
    }

    override fun resize(width: Int, height: Int) {
        
    }

    override fun hide() {
        
    }

    override fun render(delta: Float) {
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        gl.glClearColor(10F, 0F, 10F, 0F)
        //batch.begin()
        drawGrid()
        handStage.active = true
        handStage.render()
        //batch.end()
    }

    override fun resume() {
        
    }

    override fun dispose() {
        super.dispose()
        handStage.dispose()
    }
}