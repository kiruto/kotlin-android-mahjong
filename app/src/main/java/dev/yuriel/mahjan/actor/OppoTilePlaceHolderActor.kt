package dev.yuriel.mahjan.actor

import com.badlogic.gdx.graphics.g2d.Batch
import dev.yuriel.kotmvp.Dev
import dev.yuriel.kotmvp.SMALL_TILE_HEIGHT
import dev.yuriel.kotmvp.SMALL_TILE_WIDTH

/**
 * Created by yuriel on 8/7/16.
 */
class OppoTilePlaceHolderActor : TileActor() {
    override fun getSize() = Pair(SMALL_TILE_WIDTH * Dev.U, SMALL_TILE_HEIGHT * Dev.U)

    override fun onDraw(batch: Batch?, parentAlpha: Float) {
        if (null == obverse) return
        batch?.draw(obverse, - position * width, 0F, width, height)
    }
}