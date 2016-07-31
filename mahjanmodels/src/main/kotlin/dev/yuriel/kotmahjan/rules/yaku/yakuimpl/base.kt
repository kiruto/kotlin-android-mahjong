/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.HaiType
import dev.yuriel.kotmahjan.models.Shuntsu

/**
 * Created by yuriel on 7/24/16.
 */
fun peiko(shuntsuList: List<Shuntsu>): Int {
    if (shuntsuList.size < 2) {
        return 0
    }

    var stockOne: Shuntsu? = null
    var stockTwo: Shuntsu? = null

    var peiko = 0
    for (shuntsu in shuntsuList) {
        //鳴いている場合はfalse
        if (shuntsu.isOpen) {
            return 0
        }

        if (stockOne == null) {
            stockOne = shuntsu
            continue
        }

        //１つ目の盃口が見つかった
        if (stockOne.equals(shuntsu) && peiko == 0) {
            peiko = 1
            continue
        }

        if (stockTwo == null) {
            stockTwo = shuntsu
            continue
        }

        if (stockTwo.equals(shuntsu)) {
            return 2
        }
    }
    return peiko
}

fun sanShokuDetectType(d: Sanshoku, shuntsuType: HaiType?) {
    if (shuntsuType == HaiType.MZ) {
        d.mz = true
    } else if (shuntsuType == HaiType.PZ) {
        d.pz = true
    } else if (shuntsuType == HaiType.SZ) {
        d.sz = true
    }
}

data class Sanshoku(var mz: Boolean = false, var pz: Boolean = false, var sz: Boolean =false) {
    fun isSanshoku(): Boolean = mz && pz && sz
}