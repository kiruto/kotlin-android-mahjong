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

import dev.yuriel.kotmahjan.models.Tehai
import org.junit.Test

/**
 * Created by yuriel on 7/27/16.
 */
class CollectionTest {

    val mgr = HaiMgr()

    fun getTehai(): Tehai {
        val tehai = Tehai()

        while(!tehai.enough()){
            tehai.put(mgr.getHai())
        }
        return tehai
    }

    @Test
    fun testAllHai() {
        val all = HaiUtil().getAllHai()
//        for (hai in all) {
//            println(hai.toString())
//        }
        assert(all.size == 9 * 3 * 4 + 7 * 4) {
            "all hai size error: ${all.size} != ${9 * 3 * 4 + 7 * 4}"
        }
        val array = IntArray(27 + 7) { i -> 0 }
        for (hai in all) {
            array[hai.id - 1] += 1
        }
        for (index in array) {
            assert(array[index] == 4) { "index($index) size: ${array[index]}" }
        }
    }

    @Test
    fun testRandAllHai() {
        val all = HaiUtil().getAllHaiRand()
//        for (hai in all) {
//            println(hai.toString())
//        }
        assert(all.size == 9 * 3 * 4 + 7 * 4) {
            "all hai size error: ${all.size} != ${9 * 3 * 4 + 7 * 4}"
        }
        val array = IntArray(27 + 7) { i -> 0 }
        for (hai in all) {
            array[hai.id - 1] += 1
        }
        for (index in array) {
            assert(array[index] == 4) { "index($index) size: ${array[index]}" }
        }
    }

    @Test
    fun testHaiPai() {
        val tehai = Tehai()
        val mgr = HaiMgr()
        for (i in 0..2) {
            tehai.put(mgr.haiPai())
        }
        tehai.put(mgr.getHai())
        tehai.sort()
        println(tehai)
        assert(tehai.haiList.size == 13)
    }
}