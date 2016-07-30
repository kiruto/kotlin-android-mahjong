package dev.yuriel.kotmahjan.ctrl

import dev.yuriel.kotmahjan.ctrl.analysis.AI
import dev.yuriel.kotmahjan.ctrl.analysis.I
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

        while (mgr.hasHai()) {
            //　スタート
            ai.receive(mgr.getHai())
            val shanten = calculateShantensu(ai.getHai())

            prl("向聴数: $shanten")
            if (shanten < 1) {
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
        testAI(I())
    }

    private fun pr(str: String) {
        print(ANSI_RED + str + ANSI_RESET)
    }

    private fun prl(str: String) {
        println(ANSI_RED + str + ANSI_RESET)
    }
}