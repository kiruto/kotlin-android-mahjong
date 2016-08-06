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

    init {
        setSize(SIDE_TILE_WIDTH * Dev.UX, SIDE_TILE_HEIGHT * Dev.UX)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        if (null == back) return
        batch?.draw(back,
                Dev.MAX_X * Dev.UX, position * width,
                SIDE_TILE_HEIGHT * Dev.UX / 2F, SIDE_TILE_WIDTH * Dev.UX / 2F,
                SIDE_TILE_WIDTH * Dev.UX, SIDE_TILE_HEIGHT * Dev.UX,
                1F, 1F, 90F
        )
    }
}