package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.models.isFonPai
import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 * 大四喜判定
 * 東・南・西・北の四種類をすべて刻子または槓子にして和了した場合に成立
 */
fun 大四喜Impl(s: MentsuSupport): Boolean {
    var fonpaiCount = 0
    for (kotsu in s.getKotsuList()) {
        if (isFonPai(kotsu.getHai())) {
            fonpaiCount++
        }
    }

    return fonpaiCount == 4
}