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

    override fun draw(batch: Batch?, parentAlpha: Float) {
        if (null == back) return
        batch?.draw(back,
                0F, - position * width, 0F, 0F,
                SIDE_TILE_WIDTH * Dev.UX, SIDE_TILE_HEIGHT * Dev.UY,
                1F, 1F, 270F
        )
    }
}