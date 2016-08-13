/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.mahjan.group

import dev.yuriel.kotmvp.SMALL_TILE_HEIGHT
import dev.yuriel.kotmvp.SMALL_TILE_MARGIN
import dev.yuriel.kotmvp.bases.BaseGroup

/**
 * Created by yuriel on 8/9/16.
 */
class FuroGroup: BaseGroup() {

    val furoList: MutableList<TileHorizontalGroup> = mutableListOf()

    fun calculate() {
        var originX = 0F
        for (f in furoList) {
            f.setPosition(originX, 0F)
            originX += f.width + SMALL_TILE_MARGIN
        }
        width = originX
        height = SMALL_TILE_HEIGHT
    }

    override fun destroy() {
        furoList.clear()
    }
}