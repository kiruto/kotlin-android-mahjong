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