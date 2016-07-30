package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 * 混老頭判定
 * 么九牌のみで構成される場合成立
 */
fun 混老頭Impl(s: MentsuSupport): Boolean {
    if (s.getShuntsuList().size > 0) {
        return false
    }
    for (toitsu in s.getToitsuList()) {
        val num = toitsu.getHai()?.num
        if (num == null || 1 < num && num < 9) {
            return false
        }
    }

    for (kotsu in s.getKotsuList()) {
        val num = kotsu.getHai()?.num
        if (num == null || 1 < num && num < 9) {
            return false
        }
    }
    return true
}