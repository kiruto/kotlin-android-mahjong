/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.models.Kotsu
import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 */
fun 三色同刻Impl(s: MentsuSupport): Boolean {
    if (s.kotsuCount < 3) {
        return false
    }

    var candidate: Kotsu? = null
    val sanshoku = Sanshoku()
    for (kotsu in s.getKotsuList()) {
        val shuntsuType = kotsu.getHai()?.type
        val shuntsuNum = kotsu.getHai()?.num

        if (candidate == null) {
            candidate = kotsu
            continue
        }

        if (candidate.getHai()?.num == shuntsuNum) {
            sanShokuDetectType(sanshoku, shuntsuType)
            sanShokuDetectType(sanshoku, candidate.getHai()?.type)
        } else {
            candidate = kotsu
        }
    }
    return sanshoku.isSanshoku()
}