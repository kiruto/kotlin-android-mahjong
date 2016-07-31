/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.models.Shuntsu
import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 */
fun 三色同順Impl(s: MentsuSupport): Boolean {
    if (s.shuntsuCount < 3) {
        return false
    }

    var candidate: Shuntsu? = null
    val sanshoku = Sanshoku()

    for (shuntsu in s.getShuntsuList()) {
        val shuntsuType = shuntsu.getHai()?.type
        val shuntsuNum = shuntsu.getHai()?.num

        if (candidate == null) {
            candidate = shuntsu
            continue
        }


        if (candidate.getHai()?.num == shuntsuNum) {
            sanShokuDetectType(sanshoku, shuntsuType)
            sanShokuDetectType(sanshoku, candidate.getHai()?.type)
        } else {
            candidate = shuntsu
        }
    }
    return sanshoku.isSanshoku()
}