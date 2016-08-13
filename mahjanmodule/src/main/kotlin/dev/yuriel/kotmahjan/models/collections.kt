/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.models

/**
 * Created by yuriel on 7/16/16.
 */

/**
 * [0,0,0,0,0,0,0,0,0,  MZ
 *  0,0,0,0,0,0,0,0,0,  PZ
 *  0,0,0,0,0,0,0,0,0,  SZ
 *  0,0,0,0,0,0,0]      TSUHAI
 */
fun toTypedHaiArray(haiList: List<Hai>, removeFuro:Boolean = true): IntArray {
    val result = IntArray(34) { i -> 0 }
    for (hai in haiList) {
        if (removeFuro && hai.hasStatus(STATUS_FURO)) continue
        result[hai.id - 1]++
    }
    return result
}

fun fromTypedHaiArray(array: IntArray): List<Hai> {
    val result = mutableListOf<Hai>()
    for (index in 0..array.size - 1) {
        if (array[index] == 0) continue
        for (h in 0..array[index]) {
            result.add(Hai.newInstance(index + 1))
        }
    }
    return result
}