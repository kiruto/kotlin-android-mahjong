/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.models.PlayerContext
import dev.yuriel.kotmahjan.models.RoundContext
import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/23/16.
 */
private val 国士無双Kata = intArrayOf(
        1, 0, 0, 0, 0, 0, 0, 0, 1,
        1, 0, 0, 0, 0, 0, 0, 0, 1,
        1, 0, 0, 0, 0, 0, 0, 0, 1,
        1, 1, 1, 1,
        1, 1, 1
)
fun 国士無双Impl(r: RoundContext?, p: PlayerContext?, s: MentsuSupport?, hai: IntArray): Boolean {
    //国士の形一個ずつ減らす
    var count = 0
    for (i in hai.indices) {
        hai[i] -= 国士無双Kata[i]

        //么九牌が無ければ(-1になったら)false
        if (hai[i] == -1) {
            return false
        }

        //么九牌以外が含まれていたらfalse
        if (国士無双Kata[i] == 0 && hai[i] > 0) {
            return false
        }
        if (hai[i] == 1) {
            count++
        }
    }
    if (count == 1) {
        //残ってるのが么九牌1つならtrue
        if (hai[0] == 1 ||
                hai[8] == 1 ||
                hai[9] == 1 ||
                hai[17] == 1 ||
                hai[18] == 1 ||
                hai[26] == 1 ||
                hai[27] == 1 ||
                hai[28] == 1 ||
                hai[29] == 1 ||
                hai[30] == 1 ||
                hai[31] == 1 ||
                hai[32] == 1 ||
                hai[33] == 1) {
            return true
        }
    }
    //残ってるのが１個以外ならfalse
    return false
}