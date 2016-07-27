package dev.yuriel.kotmahjan.ctrl

import org.junit.Test

/**
 * Created by yuriel on 7/27/16.
 */
class HaiMgrTest {

    @Test
    fun testMgr() {
        for (round in 0..7) {
            var num = 0
            var kan = 0
            val mgr = HaiMgr()
            for (i in 0..11) {
                num += mgr.haiPai().size

            }
            for (j in 0..round) {
                if (j == 0) continue
                if (mgr.couldKan()) {
                    mgr.kan()
                    kan += 1
                    println("kan")
                }
            }
            while (mgr.hasHai()) {
                mgr.getHai()
                num += 1
            }
            for (hai in mgr.doraOmote()) {
                println("dora: $hai")
            }
            for (hai in mgr.doraUra()) {
                println("doraUra: $hai")
            }

            assert(num == 3 * 4 * 9 + 7 * 4 - 14 - kan) {
                "num is $num, should be ${3 * 4 * 9 + 7 * 4 - 14 - kan}"
            }

        }
    }
}