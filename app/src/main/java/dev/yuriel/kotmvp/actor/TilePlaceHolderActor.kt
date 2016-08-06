package dev.yuriel.kotmvp.actor

import com.badlogic.gdx.graphics.Texture
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmvp.interfaces.BaseActor
import dev.yuriel.kotmvp.model.TileWrapper

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
    var position: Int = 0
        set(value) {
            if (value > -1 || value < 14) {
                field = value
            }
        }
    var tile: TileWrapper? = null
    var texture: Texture? = null
        get() = tile?.texture
        private set

    fun update(hai: Hai?) {
        tile?.hai = hai
    }

    override fun compareTo(other: TilePlaceHolderActor): Int = position - other.position
}