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

import dev.yuriel.kotmvp.*
import dev.yuriel.kotmvp.layout.RootScreen

/**
 * Created by yuriel on 8/19/16.
 */

const val SCREEN = "root_screen"
const val HANDS_BOTTOM = "hands_bottom"
const val HANDS_LEFT = "hands_left"
const val HANDS_RIGHT = "hands_right"
const val HANDS_OPPO = "hands_oppo"
const val RIVER_BOTTOM = "river_bottom"
const val RIVER_LEFT = "river_left"
const val RIVER_RIGHT = "river_right"
const val RIVER_OPPO = "river_oppo"
const val TABLE = "table"
const val TABLE_INDICATOR = "table_indicator"
//        val LAST_TILE = "last_tile"
const val BTN_CHI = "btn_chi"
const val BTN_PON = "btn_pon"
const val BTN_KAN = "btn_kan"
const val BTN_RON = "btn_ron"
const val BTN_TSUMO = "btn_tsmo"
const val BTN_NAKI = "btn_naki"
const val BTN_NO_NAKI = "btn_no_naki"

fun MainGameRootViews.layout() {

    RootScreen.layout {
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
            moveUnits(-FURO_TILE_WIDTH * 0.25, FURO_TILE_HEIGHT * 2)
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
            moveUnits(-FURO_TILE_HEIGHT * 0.3, -FURO_TILE_HEIGHT * 0.25)
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

//            relative(LAST_TILE) {
//                20 x 50
//                actor = lastTile
//                alignRightOf(TABLE_INDICATOR)
//                alignTopOf(TABLE_INDICATOR)
//            }

        relative(BTN_NAKI) {
            32 x 25
            actor = btnNaki
            alignRightOf(SCREEN)
            moveTop(275)
        }

        relative(BTN_NO_NAKI) {
            65 x 25
            actor = btnNoNaki
            alignRightOf(SCREEN)
            moveTop(275)
        }

        relative(BTN_KAN) {
            65 x 25
            actor = btnKan
            alignRightOf(SCREEN)
            below(BTN_NAKI)
            moveTop(25)
        }

        relative(BTN_CHI) {
            65 x 25
            actor = btnChi
            alignRightOf(SCREEN)
            below(BTN_KAN)
            moveBottom(25)
        }

        relative(BTN_PON) {
            65 x 25
            actor = btnPon
            alignRightOf(SCREEN)
            below(BTN_CHI)
            moveBottom(25)
        }

        relative(BTN_RON) {
            65 x 25
            actor = btnRon
            alignRightOf(SCREEN)
            above(HANDS_BOTTOM)
        }

        relative(BTN_TSUMO) {
            65 x 25
            actor = btnTsumo
            alignRightOf(SCREEN)
            above(HANDS_BOTTOM)
        }
    }
}