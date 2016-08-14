/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.mahjan.group

import dev.yuriel.kotmvp.Dev
import dev.yuriel.kotmvp.FURO_TILE_HEIGHT
import dev.yuriel.kotmvp.bases.BaseGroup
import dev.yuriel.mahjan.actor.SingleTileActor
import dev.yuriel.mahjan.enums.TileStatus
import dev.yuriel.mahjan.model.TileWrapper

/**
 * Created by yuriel on 8/9/16.
 */
class TileHorizontalGroup(tiles: List<TileWrapper>, val position: Int): BaseGroup() {

    private val tileList: MutableList<SingleTileActor> = mutableListOf()
    val size: Int
        get() = tileList.size

    init {
        update(tiles)
    }

    fun update(tiles: List<TileWrapper>) {
        tileList.clear()
        for (t in tiles) {
            val actor = SingleTileActor(t)
            tileList.add(actor)
            addActor(actor)
        }
        calculate()
    }

    fun calculate() {
        var originX = 0F
        for (t in tileList) {
            //t.setTilePosition(originX, 0F)
            t.position = originX
            when (t.status) {
                TileStatus.NORMAL,
                TileStatus.OBVERSE -> originX += t.width
                TileStatus.HORIZONTAL -> originX += t.height
            }
        }
        width = originX
        height = FURO_TILE_HEIGHT * Dev.U
    }

    override fun destroy() {
        tileList.clear()
    }
}