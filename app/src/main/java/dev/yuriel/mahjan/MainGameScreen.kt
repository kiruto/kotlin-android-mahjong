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
import java.util.*

/**
 * Created by yuriel on 8/5/16.
 */
class MainGameScreen: BaseScreen() {

    val haiList4Test: HaiMgr = HaiMgr()
    val handGroup = HandsGroup()
    val leftGroup = LeftSideGroup()
    val rightGroup = RightSideGroup()
    val rootStage = ViewStage()

    override fun preload() = listOf(TileMgr)

    override fun show() {
        rootStage.addActor(handGroup)
        rootStage.addActor(leftGroup)
        handGroup.updateList(haiList4Test.getHaiList4Test())
        leftGroup.updateList(haiList4Test.getHaiList4Test())
        rightGroup.updateList(haiList4Test.getHaiList4Test())
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
        drawGrid()
        rootStage.active = true
        rootStage.render()
    }

    override fun resume() {
        
    }

    override fun dispose() {
        super.dispose()
        rootStage.dispose()
    }
}