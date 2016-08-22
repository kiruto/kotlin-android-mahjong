/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 yuriel<yuriel3183@gmail.com>
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
 * SOFTWARE.
 */

package dev.yuriel.kotmahjan.models.collections

import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.HaiType
import java.util.*

/**
 * Created by yuriel on 8/13/16.
 */
class Tehai: Comparator<Hai>, TileCollection() {

    companion object {
        fun fromString(string: String): Tehai {
            val list: MutableList<Hai> = mutableListOf()
            if (!string.isEmpty()) {
                val haiStrings = string.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                for (haiString in haiStrings) {
                    list.add(Hai.parse(haiString))
                }
            }
            val result = Tehai()
            result.put(list)
            return result
        }
    }

//    private val haiListStoreL MutableList<Hai>

    override val haiListStore: MutableList<Hai> by lazy { ArrayList<Hai>() }

    fun put(hai: Hai) {
        haiListStore.add(hai)
        notifyDataChange()
    }

    fun put(list: Collection<Hai>) {
        haiListStore.addAll(list)
        notifyDataChange()
    }

    fun clear() {
        haiListStore.clear()
        notifyDataChange()
    }

    fun enough(): Boolean {
        return haiList.size > 12
    }

    fun sort() {
        //val result: ArrayList<Hai> = ArrayList()
        haiListStore.sortWith(this)
        val resultArray: Array<ArrayList<Hai>> = Array(HaiType.values().size, { i -> ArrayList<Hai>() })
        for (hai: Hai in haiList) {
            for (t: HaiType in HaiType.values()) {
                if (hai.type == t) {
                    resultArray[t.ordinal].add(hai)
                    continue
                }
            }
        }
        haiListStore.clear()
        for (list in resultArray) {
            haiListStore.addAll(list)
        }
        notifyDataChange()
    }

    override fun toString(): String {
        var result = ""
        for (h in haiList) {
            result += h.toString() + ","
        }
        return result.substring(0, result.length - 1)
    }

    override fun compare(hai1: Hai, hai2: Hai): Int {
        return hai1.num - hai2.num
    }
}