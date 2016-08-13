/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
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