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

import java.util.*

/**
 * Created by yuriel on 7/16/16.
 */
data class Hai(val type: HaiType, val num: Int = -1, var status: Int = STATUS_NORMAL): Comparable<Hai> {

    val id: Int by lazy { genId() }
    val idFZ: Int by lazy { id - 1 }
    constructor(type: String, num: Int = -1): this(HaiType.valueOf(type), num)

    var aka = false

    infix fun sameAs(hai: Hai): Boolean = type == hai.type && num == hai.num && aka == hai.aka

    fun hasStatus(flag: Int): Boolean {
        return status and flag == flag
    }

    fun removeStatus(flag: Int) {
        status = status and flag.inv()
    }

    fun addStatus(flag: Int) {
        status = status or flag
    }

    fun isYaochu(): Boolean = num == 1 || num == 9 || num == -1

    fun getCode(): Int {
        return id
    }

    @Throws(DoraCannotFoundException::class)
    fun nextDora(): Hai {
        val haiNum: Int =
        if (9 == num) 1
        else if (-1 != num) num + 1
        else {
            val haiId: Int = when(type) {
                HaiType.E -> 28
                HaiType.S -> 29
                HaiType.W -> 30
                HaiType.N -> 27
                HaiType.D -> 32
                HaiType.H -> 33
                HaiType.T -> 31
                else -> throw DoraCannotFoundException(this)
            }
            return newInstance(haiId)
        }
        return Hai(type, haiNum)
    }

    private fun genId(): Int {
        //(Math.log((if (-1 != num) 0x3f8040201L and type.id else type.id).toDouble()) / Math.log(2.0)).toInt()
        var rank = 9
        when(type) {
            HaiType.E -> return 28
            HaiType.S -> return 29
            HaiType.W -> return 30
            HaiType.N -> return 31
            HaiType.D -> return 32
            HaiType.H -> return 33
            HaiType.T -> return 34
            HaiType.MZ -> rank *= 0
            HaiType.PZ -> rank *= 1
            HaiType.SZ -> rank *= 2
        }
        return num + rank
    }

    override fun compareTo(other: Hai): Int = num - other.num

    override fun toString(): String {
        val type = when(type) {
            HaiType.MZ -> "m"
            HaiType.PZ -> "p"
            HaiType.SZ -> "s"
            else -> return type.name
        }
        return type + (if (aka) "+" else "") + if (0 > num && 10 < num) "" else num
    }

    override fun hashCode(): Int {
        return (3 * type.hashCode() + 4 * num.hashCode() + 5 * aka.hashCode()) / 4
    }

    override fun equals(other: Any?): Boolean {
        if (other is Hai) return sameAs(other)
        else return false
    }

    companion object factory {

        @Throws(DoraCannotFoundException::class)
        fun parse(s: String): Hai {
            if (s.length == 1) {
                val id: Int = when (s[0]) {
                    'E' -> 28
                    'S' -> 29
                    'W' -> 30
                    'N' -> 31
                    'D' -> 32
                    'H' -> 33
                    'T' -> 34
                    else -> throw ParseHaiException(s)
                }
                return newInstance(id)
            }
            val aka: Boolean
            var id: Int =
            when (s[0]) {
                'm' -> 0
                'p' -> 9
                's' -> 18
                else -> throw ParseHaiException(s)
            }
            val num: Char = if (s.length == 3 && s[1] == '+') {
                aka = true
                s[2]
            } else if (s.length == 2) {
                aka = false
                s[1]
            } else throw ParseHaiException(s)
            id += Character.getNumericValue(num)
            val result = newInstance(id)
            result.aka = aka
            return result
        }

        fun newInstance(id: Int):Hai {
            when (id) {
                28 -> return Hai(HaiType.E)
                29 -> return Hai(HaiType.S)
                30 -> return Hai(HaiType.W)
                31 -> return Hai(HaiType.N)
                32 -> return Hai(HaiType.D)
                33 -> return Hai(HaiType.H)
                34 -> return Hai(HaiType.T)
            }
            if (1 <= id && 10 > id) {
                return Hai(HaiType.MZ, id)
            } else if (10 <= id && 19 > id) {
                return Hai(HaiType.PZ, id - 9)
            } else if (19 <= id && 28 > id) {
                return Hai(HaiType.SZ, id - 18)
            } else {
                throw NoSuchTileException(id)
            }
        }
    }
}