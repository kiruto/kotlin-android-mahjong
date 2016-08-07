package dev.yuriel.mahjan.actor

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmvp.bases.BaseActor
import dev.yuriel.mahjan.model.TileWrapper

/**
 * Created by yuriel on 8/6/16.
 */
abstract class TileActor: BaseActor(), Comparable<TilePlaceHolderActor> {
    abstract fun getSize(): Pair<Float,  Float>

    init {
        setSize(getSize().first, getSize().second)
    }

    var position: Int = 0
        set(value) {
            if (value > -1 || value < 14) {
                field = value
            }
        }
    var texture: TextureRegion? = null
        get() = tile?.texture
        private set

    var back: TextureRegion? = null
        get() = tile?.back
        private set

    var tile: TileWrapper? = null
        set(value) {
            field = value

        }
    fun update(hai: Hai?) {
        if (tile == null)
            tile = TileWrapper()
        tile?.hai = hai
    }

    override fun destroy() {
        tile?.destroy()
        tile = null
    }

    override fun compareTo(other: TilePlaceHolderActor): Int = position - other.position
}