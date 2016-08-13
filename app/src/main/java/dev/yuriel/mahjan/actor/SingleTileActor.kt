/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.mahjan.actor

import com.badlogic.gdx.graphics.g2d.Batch
import dev.yuriel.kotmvp.Dev
import dev.yuriel.kotmvp.FURO_TILE_HEIGHT
import dev.yuriel.kotmvp.FURO_TILE_WIDTH
import dev.yuriel.kotmvp.bases.BaseActor
import dev.yuriel.mahjan.enums.TileStatus
import dev.yuriel.mahjan.model.TileWrapper

/**
 * Created by yuriel on 8/9/16.
 */
class SingleTileActor(private val tile: TileWrapper): BaseActor() {

    private val size = Pair(FURO_TILE_WIDTH * Dev.U, FURO_TILE_HEIGHT * Dev.U)

    init {
        width = size.first
        height = size.second
    }

    val status = tile.status
    var position: Float = 0F

    override fun destroy() {
        tile.destroy()
    }

    override fun onDraw(batch: Batch?, parentAlpha: Float) {
        if (tile.status == TileStatus.OBVERSE) {
            batch?.draw(tile.back, position, 0F, size.first, size.second)
            return
        } else {
            batch?.draw(tile.texture, position, 0F, size.first, size.second)
        }
        if (tile.status == TileStatus.HORIZONTAL) {
            rotation = 90F
        }
    }
}