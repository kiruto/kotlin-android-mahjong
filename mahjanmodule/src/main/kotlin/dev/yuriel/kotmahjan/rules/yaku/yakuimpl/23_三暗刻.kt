/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 */
fun 三暗刻Impl(s: MentsuSupport): Boolean {
    if (s.kotsuCount < 3) {
        return false
    }

    var ankoCount = 0
    for (kotsu in s.getKotsuList()) {
        if (!kotsu.isOpen) {
            ankoCount++
        }
    }
    return ankoCount == 3
}