/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.ctrl

import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.HaiType
import dev.yuriel.kotmahjan.models.MahjanException

/**
 * Created by yuriel on 7/17/16.
 */
class HaiMgr(u: HaiUtil = HaiUtil()) {

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

    fun getHaiList4Test() = haiSan

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

    @Throws(MahjanException::class)
    fun kan(): Hai {
        if (!couldKan()) {
            throw MahjanException("もうカンはできません。")
        }
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