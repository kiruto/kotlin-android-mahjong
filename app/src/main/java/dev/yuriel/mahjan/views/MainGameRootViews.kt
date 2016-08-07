package dev.yuriel.mahjan.views

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import dev.yuriel.kotmvp.views.Views
import dev.yuriel.mahjan.MockData4Test
import dev.yuriel.mahjan.group.HandsGroup
import dev.yuriel.mahjan.group.LeftSideGroup
import dev.yuriel.mahjan.group.RightSideGroup
import dev.yuriel.mahjan.stage.ViewStage

/**
 * Created by yuriel on 8/5/16.
 */
class MainGameRootViews: Views() {
    private val handGroup = HandsGroup()
    private val leftGroup = LeftSideGroup()
    private val rightGroup = RightSideGroup()

    private val layoutHelper = LayoutHelper()

    val rootStage = ViewStage()

    fun inject() {
        rootStage.addActor(handGroup)
        rootStage.addActor(leftGroup)
        rootStage.addActor(rightGroup)

        layoutHelper.calculate()
        setPosition()

    }

    fun mockLayout4Test() {
        handGroup.updateList(MockData4Test.instance.getHaiList4Test())
        leftGroup.updateList(MockData4Test.instance.getHaiList4Test())
        rightGroup.updateList(MockData4Test.instance.getHaiList4Test())
    }

    fun clearScreen() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        Gdx.gl.glClearColor(10F, 0F, 10F, 0F)
    }

    fun render() {
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
    }
}