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