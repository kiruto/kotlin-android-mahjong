package dev.yuriel.kotmvp.texture

import com.badlogic.gdx.graphics.Texture
import dev.yuriel.kotmvp.AlreadyDestroyedException

/**
 * Created by yuriel on 8/5/16.
 */
class TileMgr {
    private val map: MutableMap<String, Texture> = mutableMapOf()
    private var loaded: Boolean = false
    private var destroyed: Boolean = false

    fun load(): Boolean {
        if (destroyed)
            throw AlreadyDestroyedException()
        for (n in listOf("m", "p", "s")) {
            for (t in 1..9) {
                val fileName = "$n$t.png"
                val texture = Texture(fileName)
                map.put(fileName, texture)
            }
        }
        for (t in 1..7) {
            val fileName = "ji$t.png"
            val texture = Texture(fileName)
            map.put(fileName, texture)
        }
        for (t in 1..3) {
            val fileName = "aka$t.png"
            val texture = Texture(fileName)
            map.put(fileName, texture)
        }
        loaded = true
        return true
    }

    operator fun get(name: String): Texture? {
        return map["$name.png"]
    }

    fun destroy() {
        map.clear()
        destroyed = true
    }
}