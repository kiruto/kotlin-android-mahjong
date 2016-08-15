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

package dev.yuriel.mahjan

import dev.yuriel.kotmahjan.ctrl.HaiMgr

/**
 * Created by yuriel on 8/7/16.
 */
class MockData4Test private constructor() {
    val haiList4Test: HaiMgr = HaiMgr()
    fun getTehaiList4Test() = haiList4Test.getTehai4Test()
    fun getHaiList4Test(count: Int) = haiList4Test.getHaiList4Test(count)

    companion object {
        val instance: MockData4Test by lazy { MockData4Test() }
        val instance2: MockData4Test by lazy { MockData4Test() }
        val instance3: MockData4Test by lazy { MockData4Test() }
        val instance4: MockData4Test by lazy { MockData4Test() }
    }
}