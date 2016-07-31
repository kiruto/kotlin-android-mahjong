/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.ctrl

import dev.yuriel.kotmahjan.ai.AI
import dev.yuriel.kotmahjan.ai.Console
import dev.yuriel.kotmahjan.ai.I
import dev.yuriel.kotmahjan.ctrl.analysis.calculateShantensu
import dev.yuriel.kotmahjan.models.ANSI_RED
import dev.yuriel.kotmahjan.models.ANSI_RESET
import org.junit.Test

/**
 * Created by yuriel on 7/30/16.
 */
class TestI {
    fun testAI(ai: AI) {
        // 開局
        val mgr = HaiMgr()
        ai.clear()
        for (i in 0..2) {
            ai.store(mgr.haiPai())
        }
        ai.receive(mgr.getHai())

        var loop = 0
        var rich = false
        while (mgr.hasHai()) {
            //　スタート

            loop ++
            prl("巡: $loop")
            val hai = mgr.getHai()
            println()
            prl("配牌: $hai")
            ai.receive(hai)
            val shanten = calculateShantensu(ai.getHai())

            prl("手牌: ${ai.getHai()}")
            prl("向聴数: $shanten")
            if (!rich && shanten == 0) {
                prl("リーチ")
                rich = true
            }
            if (shanten == -1) {
                prl("ツモ")
                assert(true)
                return
            }

            ai.da(mgr.getHaiList4Test())
        }
        assert(false)
    }

    @Test
    fun testI() {
        val ai = I()
        ai.setListener(Console)
        testAI(ai)
    }

    private fun pr(str: String) {
        print(ANSI_RED + str + ANSI_RESET)
    }

    private fun prl(str: String) {
        println(ANSI_RED + str + ANSI_RESET)
    }
}