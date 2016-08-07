package dev.yuriel.mahjan.actor

import com.badlogic.gdx.graphics.g2d.Batch
import dev.yuriel.kotmvp.Dev
import dev.yuriel.kotmvp.SIDE_TILE_HEIGHT
import dev.yuriel.kotmvp.SIDE_TILE_WIDTH
import dev.yuriel.mahjan.enums.TileSide

/**
 * Created by yuriel on 8/7/16.
 */
class RightTilePlaceHolderActor: TileActor() {
    override val size = Pair(SIDE_TILE_WIDTH * Dev.UX, SIDE_TILE_HEIGHT * Dev.UY)

    override fun draw(batch: Batch?, parentAlpha: Float) {
        if (null == back) return
        batch?.draw(back,
                0F, position * width,
                SIDE_TILE_HEIGHT * Dev.UX / 2F, SIDE_TILE_WIDTH * Dev.UY / 2F,
                SIDE_TILE_WIDTH * Dev.UX, SIDE_TILE_HEIGHT * Dev.UY,
                1F, 1F, 90F
        )
    }
}