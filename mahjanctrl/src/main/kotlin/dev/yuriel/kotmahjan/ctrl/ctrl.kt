package dev.yuriel.kotmahjan.ctrl

//import dev.yuriel.kotmahjan.models.CouldNotKanException
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.HaiType

/**
 * Created by yuriel on 7/17/16.
 */

object HaiUtil {

    fun getAllHai(): MutableList<Hai> {
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
            return result
        }

        val result = mutableListOf<Hai>()
        while (!ordered.isEmpty()) {
            result.add(rand())
        }

        return result
    }

    private fun getAllHaiByType(type: HaiType): List<Hai> {
//        println(type.ordinal)
        return if (type.ordinal < 3) {
            Array(4 * 9, { i -> Hai(type, i / 4 + 1) }).toList()
        } else {
            Array(4, { i -> Hai(type, -1)}).toList()
        }
    }
}

class HaiMgr(private val u: HaiUtil = HaiUtil) {

    private val haiList: MutableList<Hai> = u.getAllHaiRand()
    private val haiSan: MutableList<Hai> = mutableListOf()
    private val ouHai: MutableList<Hai> = mutableListOf()
    private val dora: MutableList<Hai> = mutableListOf()
    private val doraUra: MutableList<Hai> = mutableListOf()

    private val preDora:  Array<Hai>
    private val preDoraUra: Array<Hai>

    init {
        haiSan.addAll(haiList.subList(0, haiList.lastIndex - 13))
        ouHai.addAll(haiList.subList(haiList.lastIndex - 14, haiList.lastIndex + 1))
        preDora = Array(5) { i -> ouHai[8 - i * 2] }
        preDoraUra = Array(5) { i -> ouHai[9 - i * 2] }
        dora.add(preDora[0])
        doraUra.add(preDoraUra[0])
    }

    fun num(): Int = haiSan.size

    fun haiPai(): List<Hai> {
        val result = mutableListOf<Hai>()
        for (i in 0..3) {
            result.add(haiSan[0])
            haiSan.removeAt(0)
        }
        return result
    }

    fun getHai(): Hai {
        val result = haiSan[0]
        haiSan.removeAt(0)
        return result
    }

    fun hasHai(): Boolean = !haiSan.isEmpty()

//    @Throws(CouldNotKanException::class)
    fun kan(): Hai {
//        if (!couldKan()) {
//            throw CouldNotKanException()
//        }
        val keyHai = haiSan.last()
        val result = ouHai.last()
        haiSan.removeAt(haiSan.lastIndex)
        ouHai.add(0, keyHai)
        ouHai.removeAt(ouHai.lastIndex)
        dora.add(preDora[dora.size])
        doraUra.add(preDoraUra[doraUra.size])
        return result
    }

    fun couldKan(): Boolean {
        return !haiSan.isEmpty() && dora.size < 5
    }

    fun doraOmote(): List<Hai> {
        return dora
    }

    fun doraUra(): List<Hai> {
        return doraUra
    }
}