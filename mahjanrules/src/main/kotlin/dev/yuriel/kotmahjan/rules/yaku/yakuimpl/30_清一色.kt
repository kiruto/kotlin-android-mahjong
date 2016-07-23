package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.models.HaiType
import dev.yuriel.kotmahjan.models.TSUHAI_ID
import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 * 清一色判定クラス
 * 萬子、索子、筒子のどれか一種の牌だけで構成された場合成立
 */
fun 清一色Impl(s: MentsuSupport): Boolean {
    val allMentsu = s.allMentsu
    val firstType = allMentsu[0].getHai()?.type

    if (firstType == null || TSUHAI_ID and firstType.id == firstType.id) {
        return false
    }

    for (mentsu in allMentsu) {
        val checkType = mentsu.getHai()?.type
        if (firstType !== checkType) {
            return false
        }
    }

    return true
}