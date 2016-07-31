/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 * 純チャン判定
 * 123の順子と789の順子、および111、999といった老頭牌の刻子もしくは槓子
 * のみの場合に成立
 */
fun 純全帯Impl(s: MentsuSupport): Boolean {
    if (s.janto == null) {
        return false
    }
    for (shuntsu in s.getShuntsuList()) {
        val num = shuntsu.getHai()?.num
        if (num != 2 && num != 8) {
            return false
        }
    }

    for (kotsu in s.getKotsuList()) {
        val num = kotsu.getHai()?.num
        if (num != 1 && num != 9) {
            return false
        }
    }

    return true
}