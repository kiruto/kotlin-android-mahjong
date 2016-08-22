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

import dev.yuriel.kotmahjan.models.*
import dev.yuriel.kotmahjan.models.collections.Shuntsu
import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 * 平和判定
 * 面子が全て順子で、雀頭が役牌でなく、待ちが両面待ちになっている場合に成立
 */
fun 平和Impl(r: RoundContext?, p: PlayerContext?, s: MentsuSupport): Boolean {
    if (s.shuntsuCount < 4) {
        return false
    }
    //雀頭が三元牌の場合はfalse
    val janto = s.janto?.getHai()
    if (isSanGen(janto)) {
        return false
    }

    if (r != null && p != null) {
        if (janto === r.getBakaze()) {
            return false
        }
        if (janto === p.getJikaze()) {
            return false
        }
    }

    var isRyanmen = false
    val last: Hai? = s.last

    /**
     * 両面待ちだったかを判定するため
     * 一つ一つの順子と最後の牌について判定する
     * @param shuntsu 判定したい順子
     * @param last    最後の牌
     * @return 両面待ちだったか
     */
    fun isRyanmen(shuntsu: Shuntsu, last: Hai?): Boolean {
        //ラスト牌と判定したい順子のtypeが違う場合はfalse
        if (shuntsu.getHai()?.type != last?.type) {
            return false
        }

        val shuntsuNum = shuntsu.getHai()?.num
        val lastNum = last?.num
        if (shuntsuNum == 2 && lastNum == 1) {
            return true
        }

        if (shuntsuNum == 8 && lastNum == 9) {
            return true
        }

        val i = shuntsuNum?.minus(lastNum?: 0)
        if (i == 1 || i == -1) {
            return true
        }

        return false
    }
    for (shuntsu in s.getShuntsuList()) {
        //鳴いていた場合もfalse
        if (shuntsu.isOpen) {
            return false
        }

        //両面待ちならそれを保存しておく
        if (isRyanmen(shuntsu, last)) {
            isRyanmen = true
        }
    }

    return isRyanmen
}