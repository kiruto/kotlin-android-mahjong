package dev.yuriel.mahjan.model

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.mahjan.texture.TileMgr

/**
 * Created by yuriel on 8/5/16.
 */
class TileWrapper() {

    constructor(hai: Hai): this() {
        this.hai = hai
    }

    var texture: TextureRegion? = null
        private set
    var display: Int = 0
        set(value) {
            field = field and value
        }
    var hai: Hai? = null
        set(value) {
            field = value
            texture = initTexture(hai = value)
        }
    var status: Int = 0
        set(value) {
            field = value
            texture = initTexture(status = value)
        }
    var selected: Boolean = false

    fun destroy() {
        hai = null
    }

    private fun initTexture(hai: Hai? = this.hai, status: Int = this.status): TextureRegion? {
        if (null == hai) return null
        TileMgr.load()
        return TileMgr[hai]
    }
}