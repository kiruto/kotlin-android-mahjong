/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 * 字一色判定
 * 字牌のみで構成された場合に成立
 */
fun 字一色Impl(s: MentsuSupport): Boolean {
    if (s.getShuntsuList().size > 0) {
        return false
    }
    if (s.janto == null) {
        for (toitsu in s.getToitsuList()) {
            if (toitsu.getHai()?.num != 0) {
                return false
            }
        }
        return true
    }

    if (s.janto!!.getHai()?.num != 0) {
        return false
    }

    for (kotsu in s.getKotsuList()) {
        if (kotsu.getHai()?.num != 0) {
            return false
        }
    }

    return true
}