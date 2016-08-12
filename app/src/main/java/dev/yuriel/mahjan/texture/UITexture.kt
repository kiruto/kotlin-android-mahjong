package dev.yuriel.mahjan.texture

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import dev.yuriel.kotmahjan.models.Hai

/**
 * Created by yuriel on 8/12/16.
 */
object UITexture: NormalTextureMgr("window.txt") {

    operator fun get(id: Int): TextureRegion? {
        return atlas?.findRegion("window_${id - 1}")
    }

    fun getBorder(): NinePatch {
        return NinePatch(this[2], this[3], this[6], this[7], this[15], this[10], this[19], this[20], this[22])
    }

    fun getSelect(): NinePatch {
        return NinePatch(this[24], 3, 3, 3, 3)
    }

    fun getBg(): TextureRegion {
        return this[1]!!
    }

    fun getBgLining(): TextureRegion {
        return this[23]!!
    }

    fun getArrowAnim(): Animation {
        val result = Animation(1F, this[25], this[26], this[27], this[28])
        result.playMode = Animation.PlayMode.LOOP_PINGPONG
        return result
    }
}