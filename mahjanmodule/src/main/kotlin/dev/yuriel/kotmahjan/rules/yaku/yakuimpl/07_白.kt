/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.models.HaiType
import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 */
fun 白Impl(s: MentsuSupport): Boolean {
    for (kotsu in s.getKotsuList()) {
        if (kotsu.getHai()?.type == HaiType.D) {
            return true
        }
    }
    return false
}