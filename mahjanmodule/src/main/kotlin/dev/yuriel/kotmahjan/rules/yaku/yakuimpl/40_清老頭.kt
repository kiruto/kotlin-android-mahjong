/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 * 清老頭判定
 * 手牌全体が老頭牌（一九牌）だけの場合成立
 *
 * 違うものが見つかったらfalseを返す方針です
 */
fun 清老頭Impl(s: MentsuSupport): Boolean {
    if (s.kotsuCount + s.kantsuCount != 4) {
        return false
    }

    var tileNum = s.janto?.getHai()?.num
    if (tileNum != 1 && tileNum != 9) {
        return false
    }

    //刻子が全て一九牌か
    for (kotsu in s.getKotsuList()) {
        tileNum = kotsu.getHai()?.num
        if (tileNum != 1 && tileNum != 9) {
            return false
        }
    }

    //ここまできたら見つかっている
    return true
}