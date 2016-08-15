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

import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.HaiType

/**
 * Created by yuriel on 7/31/16.
 */
class HaiUtil {

    private var query: String = ""

    companion object {
        fun mock(str: String): HaiUtil {
            throw UnsupportedOperationException("not implemented")
        }
    }

    fun getAllHai(): MutableList<Hai> {
        val result = mutableListOf<Hai>()
//        var i = 0
        for (type in HaiType.values()) {
//            println("${type.id}, ${type.name}, ${type.ordinal}, ${i++}")
            result.addAll(getAllHaiByType(type))
        }
        return result
    }

    fun getAllHaiRand(): MutableList<Hai> {
        val ordered: MutableList<Hai> = getAllHai()

        fun rand(): Hai {
            val randomIndex: Int = (Math.random() * ordered.size).toInt()
            val result = ordered[randomIndex]
            ordered.removeAt(randomIndex)
            query += result
            return result
        }

        val result = mutableListOf<Hai>()
        while (!ordered.isEmpty()) {
            result.add(rand())
        }

        return result
    }

    override fun toString(): String = query

    private fun getAllHaiByType(type: HaiType): List<Hai> {
//        println(type.ordinal)
        return if (type.ordinal < 3) {
            Array(4 * 9, { i -> Hai(type, i / 4 + 1) }).toList()
        } else {
            Array(4, { i -> Hai(type, -1) }).toList()
        }
    }
}