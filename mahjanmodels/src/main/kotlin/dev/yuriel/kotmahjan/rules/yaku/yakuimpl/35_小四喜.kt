/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.models.isFonPai
import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 * 小四喜判定
 * 4つの風牌東 南 西 北のうち3つを刻子1つを雀頭に含めて和了した時に成立
 * 4つのうち3つを刻子にし残る1つを雀頭にした場合
 */
fun 小四喜Impl(s: MentsuSupport): Boolean {
    if (s.janto == null) {
        return false
    }

    if (isFonPai(s.janto?.getHai())) {
        return false
    }
    if (s.kotsuCount < 3) {
        return false
    }

    var shosushiCount = 0
    for (kotsu in s.getKotsuList()) {
        if (isFonPai(kotsu.getHai())) {
            shosushiCount++
        }
    }
    return shosushiCount == 3
}