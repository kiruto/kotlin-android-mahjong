/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 * 四暗刻判定
 * 暗刻を4つ作って和了した場合成立
 * 暗槓が含まれても良い
 */
fun 四暗刻Impl(s: MentsuSupport): Boolean {
    if (s.kotsuCount + s.kantsuCount < 4) {
        return false
    }
    for (kotsu in s.getKotsuList()) {
        if (kotsu.isOpen) {
            return false
        }
    }

    return true
}