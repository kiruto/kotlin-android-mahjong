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
 * Created by yuriel on 8/13/16.
 */
fun List<Hai>.isShun(): Boolean {
    if (size != 3 || this[0].type != this[1].type || this[1].type != this[2].type) return false
    val list = Collections.sort(this)
    return this[0].num == this[1].num - 1 && this[1].num == this[2].num - 1
}

fun List<Hai>.isKo(): Boolean = size == 3 && this[0].sameAs(this[1]) && this[1].sameAs(this[2])

fun List<Hai>.isKan(): Boolean = size == 4 && this[0].sameAs(this[1]) && this[1].sameAs(this[2]) && this[2].sameAs(this[3])

fun List<Hai>.isDoi(): Boolean = size == 2 &&  this[0].sameAs(this[1])

fun List<Hai>.isGroup(): Boolean = isShun() || isKo() || isKan() || isDoi()

fun isShun(vararg hai: Hai): Boolean = hai.toList().isShun()

fun isKo(vararg hai: Hai): Boolean = hai.toList().isKo()

fun isKan(vararg hai: Hai): Boolean = hai.toList().isKan()

fun isDoi(vararg hai: Hai): Boolean = hai.toList().isDoi()

fun isGroup(vararg hai: Hai): Boolean = hai.toList().isGroup()




private val TSUHAI_ID = 0x3f8000000L
private val SANGEN_ID = 0x380000000L
private val FONPAI_ID = 0x78000000L

fun isTsuHai(type: HaiType?): Boolean = type?.id?.and(TSUHAI_ID) == type?.id
fun isSanGen(type: HaiType?): Boolean = type?.id?.and(SANGEN_ID) == type?.id
fun isFonPai(type: HaiType?): Boolean = type?.id?.and(FONPAI_ID) == type?.id

fun isTsuHai(hai: Hai?): Boolean = isTsuHai(hai?.type)
fun isSanGen(hai: Hai?): Boolean = isSanGen(hai?.type)
fun isFonPai(hai: Hai?): Boolean = isFonPai(hai?.type)