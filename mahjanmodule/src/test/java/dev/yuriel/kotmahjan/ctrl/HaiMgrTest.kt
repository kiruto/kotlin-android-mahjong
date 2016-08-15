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