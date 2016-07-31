/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.HaiType
import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 * 緑一色判定
 * 全ての牌が緑色の場合成立
 * すなわち索子の23468 發のみの場合成立
 */
fun 緑一色Impl(s: MentsuSupport): Boolean {
    for (toitsu in s.getToitsuList()) {
        if (!isGreen(toitsu.getHai())) {
            return false
        }
    }
    for (kotsu in s.getKotsuList()) {
        if (!isGreen(kotsu.getHai())) {
            return false
        }
    }

    for (shuntsu in s.getShuntsuList()) {
        if (shuntsu.getHai()?.type != HaiType.SZ || shuntsu.getHai()?.num != 3) {
            return false
        }
    }

    return true
}

/**
 * @param tile 評価する牌
 * @return 緑の牌かどうか
 */
private fun isGreen(tile: Hai?): Boolean = tile != null && (tile.type == HaiType.H || tile.type == HaiType.SZ && tile.num in intArrayOf(2, 3, 4, 6, 8))