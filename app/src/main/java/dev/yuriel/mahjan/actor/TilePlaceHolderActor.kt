/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.mahjan.actor

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import dev.yuriel.kotmahjan.models.DoraCannotFoundException
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmvp.Dev
import dev.yuriel.kotmvp.TILE_HEIGHT
import dev.yuriel.kotmvp.TILE_WIDTH
import dev.yuriel.kotmvp.bases.BaseActor
import dev.yuriel.mahjan.model.TileWrapper

/**
 * Created by yuriel on 8/5/16.
 */
class TilePlaceHolderActor(private var position: Int, isTsumo: Boolean = false): TileActor(isTsumo){
    override fun getTileSize() = Pair(TILE_WIDTH * Dev.UX, TILE_HEIGHT * Dev.UY)
    override fun getTileOrigin() = Pair(width * (position + getOffset()), 0F)
    override fun getTilePosition(): Int = position
    override fun setTilePosition(value: Int) {
        position = value
    }

    override fun onDraw(batch: Batch?, parentAlpha: Float) {
        if (null == texture) return
        batch?.draw(back, originX, originY + TILE_HEIGHT * Dev.UX - 0.5F * Dev.UX
                , TILE_WIDTH * Dev.UX, TILE_HEIGHT * Dev.UX * 0.3F)
        batch?.draw(texture, originX, originY, TILE_WIDTH * Dev.UX, TILE_HEIGHT * Dev.UX)
    }

    private fun getOffset(): Float {
        return if (isTsumo) 0.5F else 0F
    }
}