/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.mahjan.actor

import com.badlogic.gdx.graphics.g2d.Batch
import dev.yuriel.kotmvp.Dev
import dev.yuriel.kotmvp.SIDE_TILE_HEIGHT
import dev.yuriel.kotmvp.SIDE_TILE_WIDTH
import dev.yuriel.mahjan.enums.TileSide

/**
 * Created by yuriel on 8/7/16.
 */
class LeftTilePlaceHolderActor: TileActor() {


    override fun getSize() = Pair(SIDE_TILE_WIDTH * Dev.UX, SIDE_TILE_HEIGHT * Dev.UY)

    override fun onDraw(batch: Batch?, parentAlpha: Float) {
        if (null == back) return
//        batch?.draw(back,
//                0F, - position * width, width / 2, height / 2,
//                SIDE_TILE_WIDTH * Dev.UX, SIDE_TILE_HEIGHT * Dev.UY,
//                1F, 1F, - 90F
//        )
        batch?.draw(back, width * position, 0F, width, height)
    }
}