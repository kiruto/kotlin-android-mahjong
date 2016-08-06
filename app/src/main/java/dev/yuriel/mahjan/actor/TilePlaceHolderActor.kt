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
class TilePlaceHolderActor: TileActor(){
    companion object {
        fun from(hai: Hai): TilePlaceHolderActor {
            val result = TilePlaceHolderActor()
            result.tile = TileWrapper(hai)
            return result
        }
    }

    init {
        setSize(TILE_WIDTH * Dev.UX, TILE_HEIGHT * Dev.UY)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        if (null == texture) return
        batch?.draw(back, width * position, TILE_HEIGHT * Dev.UX - 0.5F * Dev.UX
                , TILE_WIDTH * Dev.UX, TILE_HEIGHT * Dev.UX * 0.3F)
        batch?.draw(texture, width * position, 0F, TILE_WIDTH * Dev.UX, TILE_HEIGHT * Dev.UX)
    }
}