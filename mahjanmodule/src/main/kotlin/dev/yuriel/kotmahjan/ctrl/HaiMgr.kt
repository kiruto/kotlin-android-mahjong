/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 yuriel
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *
 */

package dev.yuriel.kotmahjan.ctrl

import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.HaiType
import dev.yuriel.kotmahjan.models.MahjanException
import java.util.*

/**
 * Created by yuriel on 7/17/16.
 */
class HaiMgr(u: HaiUtil = HaiUtil()): Comparator<Hai> {

    private val listeners = mutableMapOf<Int, (Int) -> Unit>()

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

    fun getHaiList4Test(count: Int): List<Hai> {
        val result = mutableListOf<Hai>()
        for (i in 0..count - 1) {
            result.add(getHai())
        }
        return result
    }

    fun getTehai4Test(): List<Hai> {
        val result = mutableListOf<Hai>()
        for (i in 0..2) {
            result.addAll(haiPai())
        }
        result.add(getHai())
        result.sortWith(this)
        return result
    }

    fun num(): Int = haiSan.size

    fun haiPai(): List<Hai> {
        val result = mutableListOf<Hai>()
        for (i in 0..3) {
            result.add(haiSan[0])
            haiSan.removeAt(0)
        }
        notifyDataChange()
        return result
    }

    fun getHai(): Hai {
        val result = haiSan[0]
        haiSan.removeAt(0)
        notifyDataChange()
        return result
    }

    fun hasHai(): Boolean = !haiSan.isEmpty()

    fun listen(id: Int, listener: (Int) -> Unit) {
        listeners.put(id, listener)
    }

    fun listenAll(listener: Map<out Int, (Int) -> Unit>) {
        listeners.clear()
        listeners.putAll(listener)
    }

    private fun notifyDataChange() {
        synchronized(haiSan) {
            for ((id, l) in listeners) {
                l.invoke(haiSan.size)
            }
        }
    }

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

    override fun compare(hai1: Hai, hai2: Hai): Int {
        return hai1.num - hai2.num
    }
}