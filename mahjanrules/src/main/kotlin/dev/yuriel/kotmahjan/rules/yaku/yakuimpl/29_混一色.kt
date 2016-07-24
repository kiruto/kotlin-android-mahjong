package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.models.HaiType
import dev.yuriel.kotmahjan.models.Mentsu
import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 * 混一色判定クラス
 * 萬子、索子、筒子のどれか一種と、字牌のみで構成される場合成立
 */
fun 混一色Impl(s: MentsuSupport): Boolean {
    var hasJihai = false
    var type: HaiType? = null

    fun hasOnlyOneType(mentsu: Mentsu): Boolean {
        if (mentsu.getHai()?.num == 0) {
            hasJihai = true
        } else if (type == null) {
            type = mentsu.getHai()?.type
        } else if (type != mentsu.getHai()?.type) {
            return false
        }
        return true
    }

    for (mentsu in s.allMentsu) {
        if (!hasOnlyOneType(mentsu)) {
            return false
        }
    }

    return hasJihai
}