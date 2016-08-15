/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 yuriel
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *
 */

package dev.yuriel.kotmahjan.ctrl

import dev.yuriel.kotmahjan.ai.AI
import dev.yuriel.kotmahjan.ai.Console
import dev.yuriel.kotmahjan.ai.I
import dev.yuriel.kotmahjan.ai.analysis.calculateShantensu
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
            val shanten = calculateShantensu(ai.getHai(), 0)

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