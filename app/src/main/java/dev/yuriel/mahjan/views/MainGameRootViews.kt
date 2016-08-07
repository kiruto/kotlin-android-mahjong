package dev.yuriel.mahjan.views

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable
import dev.yuriel.kotmvp.Dev
import dev.yuriel.kotmvp.views.Views
import dev.yuriel.mahjan.MockData4Test
import dev.yuriel.mahjan.group.HandsGroup
import dev.yuriel.mahjan.group.LeftSideGroup
import dev.yuriel.mahjan.group.OpposideGroup
import dev.yuriel.mahjan.group.RightSideGroup
import dev.yuriel.mahjan.stage.ViewStage

/**
 * Created by yuriel on 8/5/16.
 */
class MainGameRootViews: Views() {
    private val desk = Image()
    private val handGroup = HandsGroup()
    private val leftGroup = LeftSideGroup()
    private val rightGroup = RightSideGroup()
    private val oppoGroup = OpposideGroup()

    private val layoutHelper = LayoutHelper()

    val rootStage = ViewStage()

    fun inject() {
        //desk.drawable = SpriteDrawable(Sprite(Texture("table.jpg")))
        desk.width = Dev.getDefaultWidth()
        desk.height = Dev.getDefaultHeight()

        rootStage.addActor(desk)
        rootStage.addActor(handGroup)
        rootStage.addActor(leftGroup)
        rootStage.addActor(rightGroup)
        rootStage.addActor(oppoGroup)

        layoutHelper.calculate()
        setPosition()

    }

    fun mockLayout4Test() {
        handGroup.updateList(MockData4Test.instance.getHaiList4Test())
        leftGroup.updateList(MockData4Test.instance.getHaiList4Test())
        rightGroup.updateList(MockData4Test.instance.getHaiList4Test())
        oppoGroup.updateList(MockData4Test.instance.getHaiList4Test())
    }

    fun clearScreen() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        Gdx.gl.glClearColor(0F, 0F, 0F, 0F)
    }

    fun render() {
        drawDesk()
        rootStage.active = true
        rootStage.render()
    }

    fun destroy() {
        rootStage.dispose()
    }

    private fun setPosition() {
        handGroup.setPosition(layoutHelper.handsBottomLayout.origin.x,
                layoutHelper.handsBottomLayout.origin.y)
        leftGroup.setPosition(layoutHelper.handsLeftLayout.origin.x,
                layoutHelper.handsLeftLayout.top())
        rightGroup.setPosition(layoutHelper.handsRightLayout.origin.x,
                layoutHelper.handsRightLayout.origin.y)
        oppoGroup.setPosition(layoutHelper.handsOppoLayout.right(),
                layoutHelper.handsOppoLayout.origin.y)
    }

    private fun drawDesk() {

    }
}