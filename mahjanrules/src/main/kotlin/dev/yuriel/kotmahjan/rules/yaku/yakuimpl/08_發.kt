package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.models.HaiType
import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 */
fun 發Impl(s: MentsuSupport): Boolean {
    for (kotsu in s.getKotsuList()) {
        if (kotsu.getHai()?.type == HaiType.H) {
            return true
        }
    }
    return false
}