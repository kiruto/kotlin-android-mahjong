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
class TilePlaceHolderActor: BaseActor(), Comparable<TilePlaceHolderActor>{
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

    var position: Int = 0
        set(value) {
            if (value > -1 || value < 14) {
                field = value
            }
        }
    var tile: TileWrapper? = null
        set(value) {
            field = value

        }
    var texture: TextureRegion? = null
        get() = tile?.texture
        private set

    val image: Image by lazy {
        val result = Image(texture)
        result.setSize(TILE_WIDTH * Dev.UX, TILE_HEIGHT * Dev.UY)
        result
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        if (null == texture) return
//        val a = Sprite(texture)
//        a.draw(batch)
        batch?.draw(texture, width * position, 0F, TILE_WIDTH * Dev.UX, TILE_HEIGHT * Dev.UX)
//        image.setPosition(width * position, 0F)
//        image.draw(batch, parentAlpha)
    }

    fun update(hai: Hai?) {
        if (tile == null)
            tile = TileWrapper()
        tile?.hai = hai
    }

    fun destroy() {
        tile?.destroy()
        tile = null
    }

    override fun compareTo(other: TilePlaceHolderActor): Int = position - other.position
}