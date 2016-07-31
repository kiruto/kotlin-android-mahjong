/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.models.PlayerContext
import dev.yuriel.kotmahjan.models.RoundContext
import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/23/16.
 */

fun 場風牌Impl(r: RoundContext?, p: PlayerContext?, s: MentsuSupport): Boolean {
    if (r == null || p == null) return false
    for (kotsu in s.kotsuKantsu) {
        if (kotsu.getHai()?.sameAs(r.getBakaze()) == true) {
            return true
        }
    }
    return false
}