/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.ctrl

import dev.yuriel.kotmahjan.ai.analysis.NUM_HAI_ID
import dev.yuriel.kotmahjan.models.Hai
import java.util.*

/**
 * Created by yuriel on 7/26/16.
 */
fun readHaiList(string: String): List<Hai> {
    if (string.isEmpty()) {
        return ArrayList()
    }
    val haiStrings = string.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    val haiList = ArrayList<Hai>()
    for (haiString in haiStrings) {
        haiList.add(Hai.parse(haiString))
    }
    return haiList
}

fun createRemainingVector(tehais: List<Hai>, otherVisibleHais: List<Hai>): IntArray {
    val remainingVector = IntArray(NUM_HAI_ID)
    Arrays.fill(remainingVector, 4)
    for (tehai in tehais) {
        remainingVector[tehai.id]--
    }
    for (otherVisibleHai in otherVisibleHais) {
        remainingVector[otherVisibleHai.id]--
    }
    return remainingVector
}

fun println(flag: Boolean, vararg o: Any) {
    var s = ""
    for (obj in o) {
        s += obj.toString() + ". "
    }
    println(s)
}