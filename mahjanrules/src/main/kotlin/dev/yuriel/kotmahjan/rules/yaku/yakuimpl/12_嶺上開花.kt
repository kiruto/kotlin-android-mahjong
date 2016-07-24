package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.models.PlayerContext
import dev.yuriel.kotmahjan.models.RoundContext
import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 */
fun 嶺上開花Impl(r: RoundContext?, p: PlayerContext?, s: MentsuSupport): Boolean {
    if (p == null) {
        return false
    }

    if (s.kantsuCount == 0) {
        return false
    }

    return p.isRinshankaihoh()
}