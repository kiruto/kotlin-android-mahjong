package dev.yuriel.mahjan.group

import dev.yuriel.kotmvp.SMALL_TILE_HEIGHT
import dev.yuriel.kotmvp.bases.BaseGroup
import dev.yuriel.mahjan.actor.SingleTileActor
import dev.yuriel.mahjan.enums.TileStatus
import dev.yuriel.mahjan.model.TileWrapper

/**
 * Created by yuriel on 8/9/16.
 */
class FuroGroup(tiles: List<TileWrapper>, val position: Int): BaseGroup() {

    private val tileList: MutableList<SingleTileActor> = mutableListOf()
    val size: Int
        get() = tileList.size

    init {
        update(tiles)
        height = SMALL_TILE_HEIGHT
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
            t.setPosition(originX, 0F)
            when (t.status) {
                TileStatus.NORMAL,
                TileStatus.OBVERSE -> originX += t.width
                TileStatus.HORIZONTAL -> originX += t.height
            }
        }
    }

    override fun destroy() {
        tileList.clear()
    }
}