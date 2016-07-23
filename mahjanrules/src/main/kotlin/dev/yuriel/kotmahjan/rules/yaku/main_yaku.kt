package dev.yuriel.kotmahjan.rules.yaku

import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/21/16.
 */

val リーチ = Yaku(1L shl 1, 1, 0, "リーチ") { s -> null }
val 一発 = Yaku(1L shl 2, 1, 0, "一発") { s -> null }
val 門前清模和 = Yaku(1L shl 3, 1, 0, "門前清模和") { s -> null }
val 平和 = Yaku(1L shl 4, 1, 0, "平和") { s -> null }
val 断ヤオ = Yaku(1L shl 5, 1, 0, "断ヤオ") { s -> null }
val 一盃口 = Yaku(1L shl 6, 1, 0, "一盃口") { s -> null }
val 白 = Yaku(1L shl 7, 1, 1, "白") { s -> null }
val 發 = Yaku(1L shl 8, 1, 1, "發") { s -> null }
val 中 = Yaku(1L shl 9, 1, 1, "中") { s -> null }
val 門風牌 = Yaku(1L shl 10, 1, 1, "門風牌") { s -> null }
val 荘風牌 = Yaku(1L shl 11, 1, 1, "荘風牌") { s -> null }
val 嶺上開花 = Yaku(1L shl 12, 1, 1, "嶺上開花") { s -> null }
val 槍槓 = Yaku(1L shl 13, 1, 1, "槍槓") { s -> null }
val 海底撈月 = Yaku(1L shl 14, 1, 1, "海底撈月") { s -> null }
val 河底撈魚 = Yaku(1L shl 15, 1, 1, "河底撈魚") { s -> null }
val ダブルリーチ = Yaku(1L shl 16, 2, 0, "ダブルリーチ") { s -> null }
val 全帯 = Yaku(1L shl 17, 2, 1, "全帯") { s -> null }
val 混老頭 = Yaku(1L shl 18, 2, 1, "混老頭") { s -> null }
val 三色同順 = Yaku(1L shl 19, 2, 2, "三色同順") { s -> null }
val 一気通貫 = Yaku(1L shl 20, 2, 2, "一気通貫") { s -> null }
val 対々和 = Yaku(1L shl 21, 2, 2, "対々和") { s -> null }
val 三色同刻 = Yaku(1L shl 22, 2, 2, "三色同刻") { s -> null }
val 三暗刻 = Yaku(1L shl 23, 2, 2, "三暗刻") { s -> null }
val 三槓子 = Yaku(1L shl 24, 2, 2, "三槓子") { s -> null }
val 小三元 = Yaku(1L shl 25, 2, 2, "小三元") { s -> null }
val 七対子 = Yaku(1L shl 26, 2, 0, "七対子") { s -> null }
val 二盃口 = Yaku(1L shl 27, 3, 0, "二盃口") { s -> null }
val 純全帯 = Yaku(1L shl 28, 3, 3, "純全帯") { s -> null }
val 混一色 = Yaku(1L shl 29, 3, 3, "混一色") { s -> null }
val 清一色 = Yaku(1L shl 30, 6, 5, "清一色") { s -> null }
val 四暗刻 = Yaku(1L shl 31, 13, 0, "四暗刻") { s -> null }
val 四暗刻単騎待ち = Yaku(1L shl 32, 26, 0, "四暗刻単騎待ち") { s -> null }
val 大三元 = Yaku(1L shl 33, 13, 13, "大三元") { s -> null }
val 字一色 = Yaku(1L shl 34, 13, 13, "字一色") { s -> null }
val 四喜和 = Yaku(1L shl 35, 13, 13, "四喜和") { s -> null }
val 大四喜 = Yaku(1L shl 36, 26, 26, "大四喜") { s -> null }
val 緑一色 = Yaku(1L shl 37, 13, 13, "緑一色") { s -> null }
val 九蓮宝燈 = Yaku(1L shl 38, 13, 0, "九蓮宝燈") { s -> null }
val 純正九連宝燈 = Yaku(1L shl 39, 26, 0, "純正九連宝燈") { s -> null }
val 清老頭 = Yaku(1L shl 40, 13, 13, "清老頭") { s -> null }
val 四槓子 = Yaku(1L shl 41, 13, 13, "四槓子") { s -> null }
val 国士無双 = Yaku(1L shl 42, 13, 0, "国士無双") { s -> null }
val 国士無双十三面待ち = Yaku(1L shl 43, 26, 0, "国士無双十三面待ち") { s -> null }
val 天和 = Yaku(1L shl 44, 13, 0, "天和") { s -> null }
val 地和 = Yaku(1L shl 45, 13, 0, "地和") { s -> null }
val ドラ = Yaku(1L shl 46, 1, 1, "ドラ") { s -> null }
val 裏ドラ = Yaku(1L shl 47, 1, 0, "裏ドラ") { s -> null }
val 三連刻 = Yaku(1L shl 48, 2, 2, "三連刻") { s -> null }
val 四連刻 = Yaku(1L shl 49, 13, 13, "四連刻") { s -> null }
val 大車輪 = Yaku(1L shl 50, 13, 0, "大車輪") { s -> null }
val 人和 = Yaku(1L shl 51, 13, 0, "人和") { s -> null }
val 八連荘 = Yaku(1L shl 52, 13, 13, "八連荘") { s -> null }
val 流し満貫 = Yaku(1L shl 53, 8, 8, "流し満貫") { s -> null }

open class Yaku(val id: Long, val han: Int, val kuisagari: Int, val name: String)

open class ContextYaku(id: Long, han: Int, kuisagari: Int, name: String): Yaku(id, han, kuisagari, name)

open class NormalYaku(id: Long, han: Int, kuisagari: Int, name: String): Yaku(id, han, kuisagari, name)

open class YakuMan(id: Long, han: Int, kuisagari: Int, name: String): Yaku(id, han, kuisagari, name)

open class ExtremeYaku(id: Long, han: Int, kuisagari: Int, name: String): Yaku(id, han, kuisagari, name)

interface Matchable<in T> {
    fun isMatch(t: T): Boolean
}

interface MatchableWithContext<in T>: Matchable<T> {
    fun hasContext()
}