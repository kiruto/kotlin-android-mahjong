package dev.yuriel.mahjan.texture

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion

/**
 * Created by yuriel on 8/12/16.
 */
open class NormalTextureMgr(private val fileName: String): TextureMgr {
    protected var atlas: TextureAtlas? = null
    protected var loaded: Boolean = false

    override fun load(): Boolean {
        try {
            if (loaded)
                return true
            atlas = TextureAtlas(Gdx.files.internal(fileName))

            loaded = true
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    override fun destroy() {
        //map.clear()
        atlas?.regions?.clear()
        atlas = null
        loaded = false
    }

    protected operator fun get(name: String): TextureRegion? {
        return atlas?.findRegion(name)
    }
}