package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 * 九蓮宝燈判定
 * 門前で「1112345678999+X」の形をあがった場合に成立
 */
private val churenManzu = intArrayOf(3, 1, 1, 1, 1, 1, 1, 1, 3)

fun 九蓮宝燈Impl(s: MentsuSupport): Boolean {
    if (s.janto == null) {
        return false
    }
    if (s.janto?.getHai()?.num == -1) {
        return false
    }
    val type = s.janto?.getHai()?.type

    val churen = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
    churen[s.janto?.getHai()?.num!! - 1] = 2

    for (shuntsu in s.getShuntsuList()) {
        if (shuntsu.getHai()?.type != type) {
            return false
        }
        churen[shuntsu.getHai()!!.num - 2]++
        churen[shuntsu.getHai()!!.num - 1]++
        churen[shuntsu.getHai()!!.num]++
    }

    for (kotsu in s.getKotsuList()) {
        if (kotsu.getHai()?.type != type) {
            return false
        }
        churen[kotsu.getHai()!!.num - 1] += 3
    }

    var restOne = false
    for (i in churen.indices) {
        val num = churen[i] - churenManzu[i]
        if (num == 1 && !restOne) {
            restOne = true
            continue
        }

        if (num == 1) {
            return false
        }

        if (num < 0 || num > 1) {
            return false
        }
    }
    return true
}