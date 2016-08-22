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

package dev.yuriel.kotmahjan.rules.yaku

import dev.yuriel.kotmahjan.models.PlayerContext
import dev.yuriel.kotmahjan.models.RoundContext
import dev.yuriel.kotmahjan.rules.MentsuSupport
import dev.yuriel.kotmahjan.rules.yaku.yakuimpl.*

/**
 * Created by yuriel on 7/21/16.
 */

val リーチ = ContextYaku(1, 1, 0, "リーチ", ::リーチImpl)
val 一発 = ContextYaku(2, 1, 0, "一発", ::一発Impl)
val 門前清模和 = ContextYaku(3, 1, 0, "門前清模和", ::門前清模和Impl)
val 平和 = ContextYaku(4, 1, 0, "平和", ::平和Impl)
val 断ヤオ = MatchableYaku(5, 1, 0, "断ヤオ", ::断ヤオImpl)
val 一盃口 = MatchableYaku(6, 1, 0, "一盃口", ::一盃口Impl)
val 白 = MatchableYaku(7, 1, 1, "白", ::白Impl)
val 發 = MatchableYaku(8, 1, 1, "發", ::發Impl)
val 中 = MatchableYaku(9, 1, 1, "中", ::中Impl)
val 自風牌 = ContextYaku(10, 1, 1, "自風牌", ::自風牌Impl)
val 場風牌 = ContextYaku(11, 1, 1, "場風牌", ::場風牌Impl)
val 嶺上開花 = ContextYaku(12, 1, 1, "嶺上開花", ::嶺上開花Impl)
val 槍槓 = ContextYaku(13, 1, 1, "槍槓", ::槍槓Impl)
val 海底撈月 = ContextYaku(14, 1, 1, "海底撈月", ::海底撈月Impl)
val 河底撈魚 = ContextYaku(15, 1, 1, "河底撈魚", ::河底撈魚Impl)
val ダブルリーチ = ContextYaku(16, 2, 0, "ダブルリーチ", ::ダブルリーチImpl)
val 全帯 = MatchableYaku(17, 2, 1, "全帯", ::全帯Impl)
val 混老頭 = MatchableYaku(18, 2, 1, "混老頭", ::混老頭Impl)
val 三色同順 = MatchableYaku(19, 2, 2, "三色同順", ::三色同順Impl)
val 一気通貫 = MatchableYaku(20, 2, 2, "一気通貫", ::一気通貫Impl)
val 対々和 = MatchableYaku(21, 2, 2, "対々和", ::対々和Impl)
val 三色同刻 = MatchableYaku(22, 2, 2, "三色同刻", ::三色同刻Impl)
val 三暗刻 = MatchableYaku(23, 2, 2, "三暗刻", ::三暗刻Impl)
val 三槓子 = MatchableYaku(24, 2, 2, "三槓子", ::三槓子Impl)
val 小三元 = MatchableYaku(25, 2, 2, "小三元", ::小三元Impl)
val 七対子 = MatchableYaku(26, 2, 0, "七対子", ::七対子Impl)
val 二盃口 = MatchableYaku(27, 3, 0, "二盃口", ::二盃口Impl)
val 純全帯 = MatchableYaku(28, 3, 3, "純全帯", ::純全帯Impl)
val 混一色 = MatchableYaku(29, 3, 3, "混一色", ::混一色Impl)
val 清一色 = MatchableYaku(30, 6, 5, "清一色", ::清一色Impl)

val 四暗刻 = MatchableYaku(31, 13, 0, "四暗刻", ::四暗刻Impl)
// todo
val 四暗刻単騎待ち = Yaku(32, 26, 0, "四暗刻単騎待ち")
val 大三元 = MatchableYaku(33, 13, 13, "大三元", ::大三元Impl)
val 字一色 = MatchableYaku(34, 13, 13, "字一色", ::字一色Impl)
val 小四喜 = MatchableYaku(35, 13, 13, "小四喜", ::小四喜Impl)
val 大四喜 = MatchableYaku(36, 26, 26, "大四喜", ::大四喜Impl)
val 緑一色 = MatchableYaku(37, 13, 13, "緑一色", ::緑一色Impl)
val 九蓮宝燈 = MatchableYaku(38, 13, 0, "九蓮宝燈", ::九蓮宝燈Impl)
// todo
val 純正九連宝燈 = Yaku(39, 26, 0, "純正九連宝燈")
val 清老頭 = MatchableYaku(40, 13, 13, "清老頭", ::清老頭Impl)
val 四槓子 = MatchableYaku(41, 13, 13, "四槓子", ::四槓子Impl)
val 国士無双 = ExtremeYaku(42, 13, 0, "国士無双", ::国士無双Impl)
val 国士無双十三面待ち = Yaku(43, 26, 0, "国士無双十三面待ち")
val 天和 = ContextYaku(44, 13, 0, "天和", ::天和Impl)
val 地和 = ContextYaku(45, 13, 0, "地和", ::地和Impl)
// todo
val ドラ = Yaku(46, 1, 1, "ドラ")
val 裏ドラ = Yaku(47, 1, 0, "裏ドラ")
val 三連刻 = Yaku(48, 2, 2, "三連刻")
val 四連刻 = Yaku(49, 13, 13, "四連刻")
val 大車輪 = Yaku(50, 13, 0, "大車輪")
val 人和 = ContextYaku(51, 13, 0, "人和", ::人和Impl)
val 八連荘 = Yaku(52, 13, 13, "八連荘")
val 流し満貫 = Yaku(53, 8, 8, "流し満貫")

val yakuMap: MutableMap<Int, String> = mutableMapOf()
val normalYaku: Map<String, Yaku> by lazy { mutableYakuMapOf(リーチ, 一発, 門前清模和, 平和, 断ヤオ, 一盃口,
        白, 發, 中, 自風牌, 場風牌, 嶺上開花, 槍槓, 海底撈月, 河底撈魚, ダブルリーチ, 全帯, 混老頭, 三色同順,
        一気通貫, 対々和, 三色同刻, 三暗刻, 三槓子, 小三元, 七対子, 二盃口, 純全帯, 混一色, 清一色) }
val yakuMan: Map<String, Yaku> by lazy { mutableYakuMapOf(四暗刻, 大三元, 字一色, 小四喜, 大四喜, 緑一色,
        九蓮宝燈, 清老頭, 四槓子, 天和, 地和, 人和) }

fun mutableYakuMapOf(vararg yaku: Yaku): Map<String, Yaku> {
    val result: MutableMap<String, Yaku> = mutableMapOf()
    for (y in yaku) {
        result.put(y.name, y)
        yakuMap.put(y.id, y.name)
    }
    return result
}

fun initYakuRules() {

}

open class Yaku(val id: Int, val han: Int, val kuisagari: Int, val name: String) {
    val flag: Long
    init {
        flag = 1L shl id
    }
}

open class MatchableYaku(id: Int, han: Int, kuisagari: Int, name: String, private val getMatch: (MentsuSupport) -> Boolean): Yaku(id, han, kuisagari, name) {
    fun isMatch(support: MentsuSupport): Boolean = getMatch(support)
}

open class ContextYaku(id: Int, han: Int, kuisagari: Int, name: String, private val getMatch: (RoundContext?, PlayerContext?, MentsuSupport) -> Boolean): Yaku(id, han, kuisagari, name) {
    fun isMatch(roundContext: RoundContext?, playerContext: PlayerContext?, support: MentsuSupport): Boolean = getMatch(roundContext, playerContext, support)
}

open class NormalYaku(id: Int, han: Int, kuisagari: Int, name: String): Yaku(id, han, kuisagari, name)

open class YakuMan(id: Int, han: Int, kuisagari: Int, name: String): Yaku(id, han, kuisagari, name)

open class ExtremeYaku(id: Int, han: Int, kuisagari: Int, name: String, private val getMatch: (RoundContext?, PlayerContext?, MentsuSupport?, IntArray) -> Boolean): Yaku(id, han, kuisagari, name) {
    fun isMatch(roundContext: RoundContext?, playerContext: PlayerContext?, support: MentsuSupport?, hai: IntArray): Boolean = getMatch(roundContext, playerContext, support, hai)
}

interface Matchable<in T> {
    val getMatch: (t: T) -> Boolean?
}

interface MatchableWithContext<in T>: Matchable<T> {
    fun hasContext()
}