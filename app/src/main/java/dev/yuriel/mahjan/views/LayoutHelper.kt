package dev.yuriel.mahjan.views

import android.support.v4.view.ViewPager
import dev.yuriel.kotmvp.*
import dev.yuriel.kotmvp.bases.LayoutPosition
import dev.yuriel.kotmvp.bases.getScreenLayout

/**
 * Created by yuriel on 8/7/16.
 */
class LayoutHelper {
    private val X = Dev.UX
    private val Y = Dev.UY
    private val SCREEN_LAYOUT = getScreenLayout()
    val ORIGIN = Pair(0F, 0F)
    val handsBottomLayout = LayoutPosition(0F, 0F, TILE_WIDTH * X * 14.5F, TILE_HEIGHT * Y)
    val handsLeftLayout = LayoutPosition(0F, 0F, SIDE_TILE_HEIGHT * X, SIDE_TILE_WIDTH * Y * 14.5F)
    val handsRightLayout = LayoutPosition(0F, 0F, SIDE_TILE_HEIGHT * X, SIDE_TILE_WIDTH * Y * 14.5F)
    val operationScreen = SCREEN_LAYOUT.copy()


    fun calculate() {
        handsBottomLayout.centerHorizontal(SCREEN_LAYOUT)
        operationScreen.above(handsBottomLayout)
        operationScreen.origin.y = handsBottomLayout.size.height
        operationScreen.size.height -= handsBottomLayout.size.height
        operationScreen.setPadding(30 * Dev.U)

        handsLeftLayout.alignLeftOf(operationScreen)
        handsLeftLayout.alignBottomOf(operationScreen)

        handsRightLayout.alignRightOf(operationScreen)
        handsRightLayout.alignBottomOf(operationScreen)

        hack()
    }

    private fun hack() {
        handsLeftLayout.origin.y -= SIDE_TILE_WIDTH * Y / 2F
        handsRightLayout.origin.y += SIDE_TILE_WIDTH * Y / 2F
    }
}