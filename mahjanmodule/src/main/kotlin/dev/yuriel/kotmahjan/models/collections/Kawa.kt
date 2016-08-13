/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.models.collections

import dev.yuriel.kotmahjan.models.Hai
import java.util.*

/**
 * Created by yuriel on 8/13/16.
 */
class Kawa: TileCollection() {
    override val haiListStore: MutableList<Hai> by lazy { ArrayList<Hai>() }

    fun push(hai: Hai) {
        haiListStore.add(hai)
        notifyDataChange()
    }

    fun pop(): Hai {
        val hai = haiList.get(haiList.size - 1)
        haiListStore.remove(hai)
        notifyDataChange()
        return hai
    }

    fun get(): List<Hai> = haiList

    fun clear() {
        haiListStore.clear()
        notifyDataChange()
    }
}
