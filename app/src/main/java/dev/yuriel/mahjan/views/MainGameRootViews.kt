/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 yuriel
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *
 */

package dev.yuriel.mahjan.views

import android.util.Log
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import dev.yuriel.kotmahjan.ctrl.impl.Kaze
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.PlayerModel
import dev.yuriel.kotmvp.*
import dev.yuriel.kotmvp.layout.RootScreen.Companion.layout
import dev.yuriel.kotmvp.views.Views
import dev.yuriel.mahjan.MockData4Test
import dev.yuriel.mahjan.actor.TileActor
import dev.yuriel.mahjan.group.*
import dev.yuriel.mahjan.interfaces.MainScreenPresenter
import dev.yuriel.mahjan.interfaces.PlayViewsInterface
import dev.yuriel.mahjan.stage.ViewStage

/**
 * Created by yuriel on 8/5/16.
 */
class MainGameRootViews(val presenter: MainScreenPresenter): Views(), PlayViewsInterface {
    private val background = Texture("table.jpg")
    private val handGroup = HandsGroup()
    private val leftGroup = LeftSideGroup()
    private val rightGroup = RightSideGroup()
    private val oppoGroup = OpposideGroup()

    private val riverBottomGroup = RiverGroup()
    private val riverLeftGroup = RiverGroup()
    private val riverRightGroup = RiverGroup()
    private val riverOppoGroup = RiverGroup()

    private val centerTableGroup = CenterTableGroup() {
        presenter.startRound()
    }


    val rootStage = ViewStage()

    private fun layout() {

        val SCREEN = "root_screen"
        val HANDS_BOTTOM = "hands_bottom"
        val HANDS_LEFT = "hands_left"
        val HANDS_RIGHT = "hands_right"
        val HANDS_OPPO = "hands_oppo"
        val RIVER_BOTTOM = "river_bottom"
        val RIVER_LEFT = "river_left"
        val RIVER_RIGHT = "river_right"
        val RIVER_OPPO = "river_oppo"
        val TABLE = "table"
        val TABLE_INDICATOR = "table_indicator"

        layout {
            id = SCREEN
            unit = Dev.U

            relative(HANDS_BOTTOM) {
                TILE_WIDTH * 14.5 x TILE_HEIGHT * 1.5
                actor = handGroup
                centerHorizontal(SCREEN)
            }

            relative(TABLE) {
                TABLE_AREA_WIDTH x TABLE_AREA_HEIGHT
                above(HANDS_BOTTOM)
                centerHorizontal(HANDS_BOTTOM)
            }

            relative(HANDS_LEFT) {
                SIDE_TILE_HEIGHT x SIDE_TILE_WIDTH * 14.5
                actor = leftGroup
                toLeftOf(TABLE)
                alignTopOf(TABLE)
                move(0F, height)
            }

            relative(HANDS_RIGHT) {
                SIDE_TILE_HEIGHT x SIDE_TILE_WIDTH * 14.5
                actor = rightGroup
                toRightOf(TABLE)
                alignBottomOf(TABLE)
            }

            relative(HANDS_OPPO) {
                SMALL_TILE_WIDTH * 14.5 x SMALL_TILE_HEIGHT
                actor = oppoGroup
                above(TABLE)
                centerHorizontal(TABLE)
                moveUnits(SMALL_TILE_WIDTH * 13.5, 0)
            }

            relative(RIVER_BOTTOM) {
                FURO_TILE_WIDTH * 6 x FURO_TILE_HEIGHT * 3
                actor = riverBottomGroup
                centerHorizontal(TABLE)
                alignBottomOf(TABLE)
                moveUnits(- FURO_TILE_WIDTH * 0.25, FURO_TILE_HEIGHT * 2)
            }

            relative(RIVER_LEFT) {
                FURO_TILE_HEIGHT * 3 x FURO_TILE_WIDTH * 6
                actor = riverLeftGroup
                centerVertical(TABLE)
                alignLeftOf(TABLE)
                moveUnits(FURO_TILE_HEIGHT * 2.95, FURO_TILE_HEIGHT * 4.25)
            }

            relative(RIVER_RIGHT) {
                FURO_TILE_HEIGHT * 3 x FURO_TILE_WIDTH * 6
                actor = riverRightGroup
                centerVertical(TABLE)
                alignRightOf(TABLE)
                moveUnits(- FURO_TILE_HEIGHT * 0.3, - FURO_TILE_HEIGHT * 0.25)
            }

            relative(RIVER_OPPO) {
                FURO_TILE_WIDTH * 6 x FURO_TILE_HEIGHT * 3
                actor = riverOppoGroup
                centerHorizontal(TABLE)
                alignTopOf(TABLE)
                moveUnits(FURO_TILE_WIDTH * 5.75, FURO_TILE_HEIGHT * 0.6)
            }

            absolute(TABLE_INDICATOR) {
                actor = centerTableGroup
                rect(RIVER_OPPO.bottom(), RIVER_RIGHT.left(), RIVER_BOTTOM.top(), RIVER_LEFT.right())
                moveUnits(FURO_TILE_HEIGHT * 1.25, FURO_TILE_HEIGHT * 0.25)
            }
        }
    }

    override fun updateKawaFor(position: Int, haiList: List<Hai>) {
        val river: RiverGroup = when (position) {
            0 -> riverBottomGroup
            1 -> riverRightGroup
            2 -> riverOppoGroup
            3 -> riverLeftGroup
            else -> return
        }
        river.update(haiList)
    }

    override fun updateTehaiFor(position: Int, haiList: List<Hai>) {
        val hands: TileGroup<*> = when (position) {
            0 -> handGroup
            1 -> rightGroup
            2 -> oppoGroup
            3 -> leftGroup
            else -> return
        }
        hands.updateList(haiList)
    }

    override fun updateTsumoFor(position: Int, hai: Hai?) {
        val hands: TileGroup<*> = when (position) {
            0 -> handGroup
            1 -> rightGroup
            2 -> oppoGroup
            3 -> leftGroup
            else -> return
        }
        hands.updateTsumo(hai)
    }

    override fun updateHaisanLast(last: Int) {
        Log.d("last", last.toString())
    }

    override fun updateRoundText(roundText: String) {
        centerTableGroup.idText.text = roundText
    }

    fun inject() {
        rootStage.addActor(handGroup)
        rootStage.addActor(leftGroup)
        rootStage.addActor(rightGroup)
        rootStage.addActor(oppoGroup)

        rootStage.addActor(riverBottomGroup)
        rootStage.addActor(riverLeftGroup)
        rootStage.addActor(riverRightGroup)
        rootStage.addActor(riverOppoGroup)

        rootStage.addActor(centerTableGroup)

        setPosition()

        setAction()
    }

    fun setAction() {
        handGroup.addOnActionListener(presenter.getActionListener())
    }

    fun mockLayout4Test() {
        handGroup.updateList(MockData4Test.instance.getTehaiList4Test())
        leftGroup.updateList(MockData4Test.instance.getTehaiList4Test())
        rightGroup.updateList(MockData4Test.instance.getTehaiList4Test())
        oppoGroup.updateList(MockData4Test.instance.getTehaiList4Test())

        riverBottomGroup.update(MockData4Test.instance2.getHaiList4Test(19))
        riverLeftGroup.update(MockData4Test.instance2.getHaiList4Test(19))
        riverRightGroup.update(MockData4Test.instance2.getHaiList4Test(19))
        riverOppoGroup.update(MockData4Test.instance2.getHaiList4Test(19))

        centerTableGroup.idText.text = "東一局"
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

        leftGroup.rotateBy(270F)
        rightGroup.rotateBy(90F)

        riverLeftGroup.rotateBy(270F)
        riverRightGroup.rotateBy(90F)
        riverOppoGroup.rotateBy(180F)

        layout()
    }

    private fun drawDesk() {
        rootStage.batch.begin()
        rootStage.batch.draw(background, 0F, 0F, Dev.getDefaultWidth(), Dev.getDefaultHeight())
        rootStage.batch.end()
    }


}