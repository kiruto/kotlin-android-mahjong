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

import dev.yuriel.kotmahjan.models.PlayerContext
import dev.yuriel.kotmahjan.models.RoundContext
import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/23/16.
 */
private val 国士無双Kata = intArrayOf(
        1, 0, 0, 0, 0, 0, 0, 0, 1,
        1, 0, 0, 0, 0, 0, 0, 0, 1,
        1, 0, 0, 0, 0, 0, 0, 0, 1,
        1, 1, 1, 1,
        1, 1, 1
)
fun 国士無双Impl(r: RoundContext?, p: PlayerContext?, s: MentsuSupport?, hai: IntArray): Boolean {
    //国士の形一個ずつ減らす
    var count = 0
    for (i in hai.indices) {
        hai[i] -= 国士無双Kata[i]

        //么九牌が無ければ(-1になったら)false
        if (hai[i] == -1) {
            return false
        }

        //么九牌以外が含まれていたらfalse
        if (国士無双Kata[i] == 0 && hai[i] > 0) {
            return false
        }
        if (hai[i] == 1) {
            count++
        }
    }
    if (count == 1) {
        //残ってるのが么九牌1つならtrue
        if (hai[0] == 1 ||
                hai[8] == 1 ||
                hai[9] == 1 ||
                hai[17] == 1 ||
                hai[18] == 1 ||
                hai[26] == 1 ||
                hai[27] == 1 ||
                hai[28] == 1 ||
                hai[29] == 1 ||
                hai[30] == 1 ||
                hai[31] == 1 ||
                hai[32] == 1 ||
                hai[33] == 1) {
            return true
        }
    }
    //残ってるのが１個以外ならfalse
    return false
}