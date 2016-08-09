package dev.yuriel.mahjan.actor

import com.badlogic.gdx.graphics.g2d.Batch
import dev.yuriel.kotmvp.SMALL_TILE_HEIGHT
import dev.yuriel.kotmvp.SMALL_TILE_WIDTH
import dev.yuriel.kotmvp.bases.BaseActor
import dev.yuriel.mahjan.enums.TileStatus
import dev.yuriel.mahjan.model.TileWrapper

/**
 * Created by yuriel on 8/9/16.
 */
class SingleTileActor(private val tile: TileWrapper): BaseActor() {

    private val size = Pair(SMALL_TILE_WIDTH, SMALL_TILE_HEIGHT)

    init {
        width = size.first
        height = size.second
    }

    val status = tile.status

    override fun destroy() {
        tile.destroy()
    }

    override fun onDraw(batch: Batch?, parentAlpha: Float) {
        if (tile.status == TileStatus.OBVERSE) {
            batch?.draw(tile.back, size.first, size.second)
            return
        } else {
            batch?.draw(tile.texture, size.first, size.second)
        }
        if (tile.status == TileStatus.HORIZONTAL) {
            rotation = 90F
        }
    }
}