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

package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.HaiType
import dev.yuriel.kotmahjan.models.collections.Shuntsu

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