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