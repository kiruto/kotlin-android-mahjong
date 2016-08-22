/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 yuriel<yuriel3183@gmail.com>
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
 * SOFTWARE.
 */

package dev.yuriel.kotmahjan.models

/**
 * Created by yuriel on 8/13/16.
 */
interface LoopContext {
    fun onStart()

    fun onRichi()
    fun onChi()
    fun onPon()
    fun onKan()
    fun onRon()

    /**
     * ツモる時
     */
    fun tsumo()
    fun tsumoAfterKan()

    /**
     * 牌を捨てる時
     */
    fun sute()

    /**
     * 全て完了
     */
    fun onEnd()

    /**
     * 九種九牌
     */
    fun is9x9Hai(): Boolean

    fun couldRichi(): Boolean
    fun couldChi(): Boolean
    fun couldPon(): Boolean
    fun couldKan(): Boolean
    fun couldRon(): Boolean
    fun hasAction(): Boolean {
        return couldChi() || couldPon() || couldKan() || couldRon()
    }

    fun waitForAction()
    fun getAction(): RoundEvent
}