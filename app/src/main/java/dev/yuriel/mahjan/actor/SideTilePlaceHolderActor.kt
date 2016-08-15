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

/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

//package dev.yuriel.mahjan.actor
//
//import com.badlogic.gdx.graphics.g2d.Batch
//import com.badlogic.gdx.graphics.g2d.TextureRegion
//import dev.yuriel.kotmvp.Dev
//import dev.yuriel.kotmvp.SIDE_TILE_HEIGHT
//import dev.yuriel.kotmvp.SIDE_TILE_WIDTH
//import dev.yuriel.kotmvp.bases.BaseActor
//import dev.yuriel.mahjan.enums.TileSide
//import dev.yuriel.mahjan.model.TileWrapper
//
///**
// * Created by yuriel on 8/6/16.
// */
//@Deprecated("use Left/Right instead")
//class SideTilePlaceHolderActor(val side: TileSide): TileActor() {
//
//    init {
//        setSize(SIDE_TILE_WIDTH * Dev.UX, SIDE_TILE_HEIGHT * Dev.UX)
//    }
//
//    override fun draw(batch: Batch?, parentAlpha: Float) {
//        if (null == back) return
//        when (side) {
//            TileSide.LEFT -> {
//                batch?.draw(back,
//                        0F, Dev.MAX_Y * Dev.UY - position * width,
//                        SIDE_TILE_HEIGHT * Dev.UX / 2F, SIDE_TILE_WIDTH * Dev.UX / 2F,
//                        SIDE_TILE_WIDTH * Dev.UX, SIDE_TILE_HEIGHT * Dev.UX,
//                        1F, 1F, 270F
//                )
//            }
//            TileSide.RIGHT -> {
//                batch?.draw(back,
//                        Dev.MAX_X * Dev.UX, position * width,
//                        SIDE_TILE_HEIGHT * Dev.UX / 2F, SIDE_TILE_WIDTH * Dev.UX / 2F,
//                        SIDE_TILE_WIDTH * Dev.UX, SIDE_TILE_HEIGHT * Dev.UX,
//                        1F, 1F, 90F
//                )
//            }
//        }
//    }
//}