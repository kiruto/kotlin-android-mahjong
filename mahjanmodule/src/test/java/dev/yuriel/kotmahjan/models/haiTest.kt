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

package dev.yuriel.kotmahjan.models

import org.junit.Test

/**
 * Created by yuriel on 7/26/16.
 */
class HaiTest {
    val haiStrings = mutableListOf<String>()
    fun initStringArray() {
        haiStrings.clear()
        for (i in 0..2) {
            val s: String =
            when(i) {
                0 -> "m"
                1 -> "p"
                2 -> "s"
                else -> ""
            }
            for (j in 1..9) {
                if (j == 5) {
                    haiStrings.add(s + "+" + j)
                } else {
                    haiStrings.add(s + j)
                }
            }
        }
        haiStrings.add("E")
        haiStrings.add("S")
        haiStrings.add("W")
        haiStrings.add("N")
        haiStrings.add("D")
        haiStrings.add("H")
        haiStrings.add("T")
    }

    @Test
    fun testShantensu() {
        val tehai = Tehai.fromString("m6,m8,p6,p7,p8,p8,p8,p9,s3,s8,s8,s9,N")
    }

    @Test
    fun testHaiFactory() {
        initStringArray()
        for (s in haiStrings) {
            val h = Hai.parse(s)
            println("index: ${haiStrings.indexOf(s)}, $s, id:${h.id}, $h")
            assert(haiStrings.indexOf(s) == h.id - 1)
            assert(s.equals(h.toString()))
        }
    }
}