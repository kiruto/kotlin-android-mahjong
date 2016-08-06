package dev.yuriel.mahjan.actor

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import dev.yuriel.kotmvp.Dev
import dev.yuriel.kotmvp.SIDE_TILE_HEIGHT
import dev.yuriel.kotmvp.SIDE_TILE_WIDTH
import dev.yuriel.kotmvp.bases.BaseActor
import dev.yuriel.mahjan.enums.TileSide
import dev.yuriel.mahjan.model.TileWrapper

/**
 * Created by yuriel on 8/6/16.
 */
class SideTilePlaceHolderActor(val side: TileSide): TileActor() {

    init {
        setSize(SIDE_TILE_WIDTH * Dev.UX, SIDE_TILE_HEIGHT * Dev.UX)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        if (null == back) return
        when (side) {
            TileSide.LEFT -> {
                batch?.draw(back,
                        0F, Dev.MAX_Y * Dev.UY - position * width,
                        SIDE_TILE_HEIGHT * Dev.UX / 2F, SIDE_TILE_WIDTH * Dev.UX / 2F,
                        SIDE_TILE_WIDTH * Dev.UX, SIDE_TILE_HEIGHT * Dev.UX,
                        1F, 1F, 270F
                )
            }
            TileSide.RIGHT -> {
                batch?.draw(back,
                        Dev.MAX_X * Dev.UX, position * width,
                        SIDE_TILE_HEIGHT * Dev.UX / 2F, SIDE_TILE_WIDTH * Dev.UX / 2F,
                        SIDE_TILE_WIDTH * Dev.UX, SIDE_TILE_HEIGHT * Dev.UX,
                        1F, 1F, 90F
                )
            }
        }
    }
}