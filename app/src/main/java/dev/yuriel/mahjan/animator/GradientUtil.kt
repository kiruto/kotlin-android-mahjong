///*
// * The MIT License (MIT)
// *
// * Copyright (c) 2016 yuriel<yuriel3183@gmail.com>
// *
// * Permission is hereby granted, free of charge, to any person obtaining a copy
// * of this software and associated documentation files (the "Software"), to deal
// * in the Software without restriction, including without limitation the rights
// * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// * copies of the Software, and to permit persons to whom the Software is
// * furnished to do so, subject to the following conditions:
// *
// * The above copyright notice and this permission notice shall be included in all
// * copies or substantial portions of the Software.
// *
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// * SOFTWARE.
// */
//
//package dev.yuriel.mahjan.animator
//
//
///**
// * Created by yuriel on 8/13/16.
// */
//object GradientUtil {
//
//    fun linear(from: Int, to: Int, current: Int): Float {
//        if (current <= from) return 0F
//        if (current >= to) return 1F
//        return (current - from).toFloat() / (to - from).toFloat()
//    }
//
//    fun gaussian(from: Int, to: Int, current: Int, f:(Number) -> Number) {
//
//    }
//
//    class linearAnimator(private val startAt: Int,
//                         private val during: Int,
//                         private val sizeA: Float,
//                         private val sizeB: Float) {
//
//        private var done: Boolean = false
//        private val k: Float
//
//        init {
//            k = sizeB - sizeA
//        }
//
//        fun get(): (i: Int) -> Pair<Float, Float> {
//            return fun (i: Int): Pair<Float, Float> {
//                val result = run(i)
//                return Pair(sizeA + result * k, sizeA + result * k)
//            }
//        }
//
//        private fun run(i: Int): Float {
//            if (done) return 1F
//            val f: Float = GradientUtil.linear(startAt, startAt + during, i)
//            if (f == 1F) done =true
//            return f
//        }
//    }
//}