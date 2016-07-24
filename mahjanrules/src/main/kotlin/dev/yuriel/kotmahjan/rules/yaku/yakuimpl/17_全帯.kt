package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 * チャンタ判定クラス
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