/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.models.collections.Shuntsu
import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 * 断么九判定
 * 么九牌（一九字牌）を一切使わず、中張牌（数牌の2〜8）のみを使って手牌を完成させた場合に成立
 */
fun 断ヤオImpl(s: MentsuSupport): Boolean {
    for (mentsu in s.allMentsu) {
        val number = mentsu.getHai()?.num
        if (number == 0 || number == 1 || number == 9) {
            return false
        }

        val shuntsuNum = mentsu.getHai()?.num
        val isEdgeShuntsu = shuntsuNum == 2 || shuntsuNum == 8
        if (mentsu is Shuntsu && isEdgeShuntsu) {
            return false
        }
    }

    return true
}