/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.mahjan.model

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.mahjan.enums.TileStatus
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

    var back: TextureRegion? = null
        private set

    var obverse: TextureRegion? = null
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
    var status: TileStatus = TileStatus.NORMAL
        set(value) {
            field = value
            texture = initTexture(status = value)
        }
    var selected: Boolean = false

    fun destroy() {
        hai = null
    }

    private fun initTexture(hai: Hai? = this.hai, status: TileStatus = this.status): TextureRegion? {
        if (null == hai) {
            back = null
            obverse = null
            return null
        } else {
            TileMgr.load()
            back = TileMgr.getBack()
            obverse = TileMgr.getObverse()
            return TileMgr[hai]
        }
    }
}