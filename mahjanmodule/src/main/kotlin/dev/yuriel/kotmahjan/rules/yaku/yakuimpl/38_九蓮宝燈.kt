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

import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 * 九蓮宝燈判定
 * 門前で「1112345678999+X」の形をあがった場合に成立
 */
private val churenManzu = intArrayOf(3, 1, 1, 1, 1, 1, 1, 1, 3)

fun 九蓮宝燈Impl(s: MentsuSupport): Boolean {
    if (s.janto == null) {
        return false
    }
    if (s.janto?.getHai()?.num == -1) {
        return false
    }
    val type = s.janto?.getHai()?.type

    val churen = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
    churen[s.janto?.getHai()?.num!! - 1] = 2

    for (shuntsu in s.getShuntsuList()) {
        if (shuntsu.getHai()?.type != type) {
            return false
        }
        churen[shuntsu.getHai()!!.num - 2]++
        churen[shuntsu.getHai()!!.num - 1]++
        churen[shuntsu.getHai()!!.num]++
    }

    for (kotsu in s.getKotsuList()) {
        if (kotsu.getHai()?.type != type) {
            return false
        }
        churen[kotsu.getHai()!!.num - 1] += 3
    }

    var restOne = false
    for (i in churen.indices) {
        val num = churen[i] - churenManzu[i]
        if (num == 1 && !restOne) {
            restOne = true
            continue
        }

        if (num == 1) {
            return false
        }

        if (num < 0 || num > 1) {
            return false
        }
    }
    return true
}