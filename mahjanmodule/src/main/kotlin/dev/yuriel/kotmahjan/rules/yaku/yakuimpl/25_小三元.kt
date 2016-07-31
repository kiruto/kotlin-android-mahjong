/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.models.isSanGen
import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 * 小三元判定
 * 三元牌のいずれかを雀頭とし、残り2つを刻子もしくは槓子にすることで成立
 */
fun 小三元Impl(s: MentsuSupport): Boolean {
    //七対子の場合はありえないのでfalse
    if (s.janto == null) {
        return false
    }

    if (isSanGen(s.janto?.getHai())) {
        return false
    }
    var count = 0
    for (kotsu in s.getKotsuList()) {
        if (isSanGen(kotsu.getHai())) {
            count++
        }
        if (count == 2) {
            return true
        }
    }

    return false
}