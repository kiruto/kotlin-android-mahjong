package dev.yuriel.kotmvp.model

import com.badlogic.gdx.graphics.Texture
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.HaiType.*
import dev.yuriel.kotmahjan.models.UnbelievableException

/**
 * Created by yuriel on 8/5/16.
 */
class TileWrapper {

    constructor(hai: Hai) {
        this.hai = hai
    }

    var texture: Texture? = null
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

    private fun initTexture(hai: Hai? = this.hai, status: Int = this.status): Texture? {
        if (null == hai) return null
        val file = findTextureFileNameByHai(hai) + ".png"
        return Texture(file)
    }
    
    private fun findTextureFileNameByHai(hai: Hai): String {
        fun normal(hai: Hai): String {
            val fileName: String
            val haiName = hai.toString()
            when (haiName.length) {
                2 -> fileName = haiName
                3 -> {
                    when(haiName.first()) {
                        'p' -> fileName = "aka1"
                        's' -> fileName = "aka2"
                        'm' -> fileName = "aka3"
                        else -> throw UnbelievableException()
                    }
                }
                else -> throw UnbelievableException()
            }
            return fileName
        }
        return when(hai.type) {
            E -> "ji1"
            S -> "ji2"
            W -> "ji3"
            N -> "ji4"
            D -> "ji5"
            H -> "ji6"
            T -> "ji7"
            else -> normal(hai)
        }
    }
}