package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.models.isSanGen
import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 * 大三元判定
 * 白・發・中の3種類をすべて刻子または槓子にして和了した場合に成立
 */
fun 大三元Impl(s: MentsuSupport): Boolean {
    var sangenCount = 0
    for (kotsu in s.getKotsuList()) {
        if (isSanGen(kotsu.getHai())) {
            sangenCount++
        }
    }

    return sangenCount == 3
}