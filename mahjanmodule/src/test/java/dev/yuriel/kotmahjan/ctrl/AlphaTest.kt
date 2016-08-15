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

import dev.yuriel.kotmahjan.ctrl.impl.AlphaClientDahaiSelector
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.Mentsu
import org.junit.Test
import java.util.*

/**
 * Created by yuriel on 7/26/16.
 */
@Deprecated("")
class AlphaTest {
    private fun selectDahai(tehaisString: String, furos: List<Mentsu>, tsumohaiString: String): Int {
        println(true, "selectDahai", tehaisString, tsumohaiString)
        val result = AlphaClientDahaiSelector.selectDahai(
                readHaiList(tehaisString), furos, Hai.parse(tsumohaiString))
        println("result: $result")
        return result
    }

    //@Test
    fun testSelectDahai() {
        assert(12L == selectDahai("m6,m8,p6,p7,p8,p8,p8,p9,s3,s8,s8,s9,N", ArrayList<Mentsu>(), "s3").toLong())
    }
}