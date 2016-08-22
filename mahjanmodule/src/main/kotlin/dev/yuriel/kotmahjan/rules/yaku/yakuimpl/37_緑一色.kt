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
import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 * 緑一色判定
 * 全ての牌が緑色の場合成立
 * すなわち索子の23468 發のみの場合成立
 */
fun 緑一色Impl(s: MentsuSupport): Boolean {
    for (toitsu in s.getToitsuList()) {
        if (!isGreen(toitsu.getHai())) {
            return false
        }
    }
    for (kotsu in s.getKotsuList()) {
        if (!isGreen(kotsu.getHai())) {
            return false
        }
    }

    for (shuntsu in s.getShuntsuList()) {
        if (shuntsu.getHai()?.type != HaiType.SZ || shuntsu.getHai()?.num != 3) {
            return false
        }
    }

    return true
}

/**
 * @param tile 評価する牌
 * @return 緑の牌かどうか
 */
private fun isGreen(tile: Hai?): Boolean = tile != null && (tile.type == HaiType.H || tile.type == HaiType.SZ && tile.num in intArrayOf(2, 3, 4, 6, 8))