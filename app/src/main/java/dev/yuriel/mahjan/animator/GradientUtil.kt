/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.mahjan.animator


/**
 * Created by yuriel on 8/13/16.
 */
object GradientUtil {

    fun linear(from: Int, to: Int, current: Int): Float {
        if (current <= from) return 0F
        if (current >= to) return 1F
        return (current - from).toFloat() / (to - from).toFloat()
    }

    fun gaussian(from: Int, to: Int, current: Int, f:(Number) -> Number) {

    }

    class linearAnimator(private val startAt: Int,
                         private val during: Int,
                         private val sizeA: Float,
                         private val sizeB: Float) {

        private var done: Boolean = false
        private val k: Float

        init {
            k = sizeB - sizeA
        }

        fun get(): (i: Int) -> Pair<Float, Float> {
            return fun (i: Int): Pair<Float, Float> {
                val result = run(i)
                return Pair(sizeA + result * k, sizeA + result * k)
            }
        }

        private fun run(i: Int): Float {
            if (done) return 1F
            val f: Float = GradientUtil.linear(startAt, startAt + during, i)
            if (f == 1F) done =true
            return f
        }
    }
}