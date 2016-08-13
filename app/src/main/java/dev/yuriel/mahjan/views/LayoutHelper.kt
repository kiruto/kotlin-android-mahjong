/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.mahjan.views

import android.support.v4.view.ViewPager
import dev.yuriel.kotmvp.*
import dev.yuriel.kotmvp.layout.LayoutPosition
import dev.yuriel.kotmvp.layout.getScreenLayout

/**
 * Created by yuriel on 8/7/16.
 */
@Deprecated("")
class LayoutHelper {
    private val U = Dev.U
    private val SCREEN_LAYOUT = getScreenLayout()
    val ORIGIN = Pair(0F, 0F)
    val handsBottomLayout = LayoutPosition(0F, 0F, TILE_WIDTH * U * 14.5F, TILE_HEIGHT * U * 1.5F)
    val handsLeftLayout = LayoutPosition(0F, 0F, SIDE_TILE_HEIGHT * U, SIDE_TILE_WIDTH * U * 14.5F)
    val handsRightLayout = LayoutPosition(0F, 0F, SIDE_TILE_HEIGHT * U, SIDE_TILE_WIDTH * U * 14.5F)
    val handsOppoLayout = LayoutPosition(0F, 0F, SMALL_TILE_WIDTH * U * 14.5F, SMALL_TILE_HEIGHT * U)
    val tableArea = LayoutPosition(0F, 0F,TABLE_AREA_WIDTH * U, TABLE_AREA_HEIGHT * U)


    fun calculate() {
        handsBottomLayout centerHorizontal SCREEN_LAYOUT

        tableArea above handsBottomLayout
        tableArea centerHorizontal SCREEN_LAYOUT
        handsLeftLayout toLeftOf tableArea
        handsLeftLayout alignTopOf tableArea
        handsRightLayout toRightOf tableArea
        handsRightLayout alignBottomOf tableArea
        handsOppoLayout above tableArea
        handsOppoLayout centerHorizontal tableArea

        hack()
    }

    private fun hack() {
        handsOppoLayout.correct(SMALL_TILE_WIDTH * U * 13.5F, 0F)

    }
}