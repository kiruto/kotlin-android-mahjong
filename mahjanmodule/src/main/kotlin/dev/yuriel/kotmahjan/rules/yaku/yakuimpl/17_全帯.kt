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

package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 * チャンタ判定
 * 123の順子と789の順子、および一九字牌の対子と刻子
 * のみで構成された場合成立
 */
fun 全帯Impl(s: MentsuSupport): Boolean {
    //雀頭がnullなら七対子なのでfalse
    if (s.janto == null) {
        return false
    }
    //雀頭が一九字牌以外ならfalse
    val jantoNum = s.janto?.getHai()?.num
    if (jantoNum != 1 && jantoNum != 9 && jantoNum != 0) {
        return false
    }

    //順子が無ければfalse
    if (s.shuntsuCount == 0) {
        return false
    }

    //順子が123の順子と789の順子でなければfalse
    for (shuntsu in s.getShuntsuList()) {
        val shuntsuNum = shuntsu.getHai()?.num
        if (shuntsuNum != 2 && shuntsuNum != 8) {
            return false
        }
    }

    //刻子・槓子が一九字牌以外ならfalse
    for (kotsu in s.kotsuKantsu) {
        val kotsuNum = kotsu.getHai()?.num
        if (kotsuNum != 1 && kotsuNum != 9 && kotsuNum != 0) {
            return false
        }
    }

    //ここまでくればtrue
    return true
}