package dev.yuriel.mahjan.views

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import dev.yuriel.kotmvp.*
import dev.yuriel.kotmvp.layout.RootScreen.Companion.layout
import dev.yuriel.kotmvp.views.Views
import dev.yuriel.mahjan.MockData4Test
import dev.yuriel.mahjan.actor.CenterFont
import dev.yuriel.mahjan.actor.CenterIndicator
import dev.yuriel.mahjan.group.*
import dev.yuriel.mahjan.stage.ViewStage

/**
 * Created by yuriel on 8/5/16.
 */
class MainGameRootViews: Views() {
//    private val desk = Image()
    private val background = Texture("table.jpg")
    private val handGroup = HandsGroup()
    private val leftGroup = LeftSideGroup()
    private val rightGroup = RightSideGroup()
    private val oppoGroup = OpposideGroup()

    private val riverBottomGroup = RiverGroup()
    private val riverLeftGroup = RiverGroup()
    private val riverRightGroup = RiverGroup()
    private val riverOppoGroup = RiverGroup()

    //private val indicator = CenterIndicator()
    //private val centerFont = CenterFont()
    private val centerTableGroup = CenterTableGroup()

    //private val layoutHelper = LayoutHelper()

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
        val CENTER_FONT = "center_font"

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

//            relative(CENTER_FONT) {
//                actor = centerFont
//                actor!!.width x actor!!.height
//                center(TABLE_INDICATOR)
//                actor {
//                    addAction(Actions.sequence(
//                            Actions.parallel(
//                                    Actions.scaleTo(0.8F, 0.8F, 0.22F),
//                                    Actions.fadeIn(0.2F)
//                            ),
//                            Actions.scaleTo(0.8F, 0.8F, 1F),
//                            Actions.scaleTo(0.3F, 0.3F, 0.4F)
//                    ))
//                }
//                //moveUnits(- FURO_TILE_WIDTH * 0.5F, FURO_TILE_HEIGHT * 2F)
//
//            }
        }
    }

    fun inject() {
//        desk.drawable = SpriteDrawable(Sprite(background))
//        desk.width = Dev.getDefaultWidth()
//        desk.height = Dev.getDefaultHeight()
//
//        rootStage.addActor(desk)
        rootStage.addActor(handGroup)
        rootStage.addActor(leftGroup)
        rootStage.addActor(rightGroup)
        rootStage.addActor(oppoGroup)

        rootStage.addActor(riverBottomGroup)
        rootStage.addActor(riverLeftGroup)
        rootStage.addActor(riverRightGroup)
        rootStage.addActor(riverOppoGroup)

//        rootStage.addActor(indicator)
//        rootStage.addActor(centerFont)
        rootStage.addActor(centerTableGroup)

        //layoutHelper.calculate()
        setPosition()

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