package dev.yuriel.kotmahjan.ctrl

import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.HaiType

/**
 * Created by yuriel on 7/31/16.
 */
class HaiUtil {

    private var query: String = ""

    companion object {
        fun mock(str: String): HaiUtil {
            throw UnsupportedOperationException("not implemented")
        }
    }

    private fun getAllHai(): MutableList<Hai> {
        val result = mutableListOf<Hai>()
//        var i = 0
        for (type in HaiType.values()) {
//            println("${type.id}, ${type.name}, ${type.ordinal}, ${i++}")
            result.addAll(getAllHaiByType(type))
        }
        return result
    }

    fun getAllHaiRand(): MutableList<Hai> {
        val ordered: MutableList<Hai> = getAllHai()

        fun rand(): Hai {
            val randomIndex: Int = (Math.random() * ordered.size).toInt()
            val result = ordered[randomIndex]
            ordered.removeAt(randomIndex)
            query += result
            return result
        }

        val result = mutableListOf<Hai>()
        while (!ordered.isEmpty()) {
            result.add(rand())
        }

        return result
    }

    override fun toString(): String = query

    private fun getAllHaiByType(type: HaiType): List<Hai> {
//        println(type.ordinal)
        return if (type.ordinal < 3) {
            Array(4 * 9, { i -> Hai(type, i / 4 + 1) }).toList()
        } else {
            Array(4, { i -> Hai(type, -1) }).toList()
        }
    }
}