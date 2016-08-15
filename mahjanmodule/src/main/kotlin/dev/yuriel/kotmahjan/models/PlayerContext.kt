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

import dev.yuriel.kotmahjan.ctrl.HaiMgr
import rx.Observable

/**
 * Created by yuriel on 7/23/16.
 */
interface PlayerContext: Comparable<PlayerContext> {
    fun onStart()
    /**
     * 配牌の階段に最後の牌を受ける
     */
    fun onReceiveHai(hai: Hai)
    fun onHaiPai(haiList: List<Hai>)
    fun onEnd()

    fun onRichi()
    fun onChi()
    fun onPon()
    fun onKan()
    fun onRon()

    fun onReceive(event: RoundEvent): RoundEventResponse
    fun getObservable(event: RoundEvent, duration: Long): Observable<RoundEvent>?

    fun isChankan(): Boolean
    fun isRich(): Boolean
    fun isDoubleRich(): Boolean
    fun isTsumo(): Boolean
    fun getJikaze(): Hai
    fun isRinshankaihoh(): Boolean
    fun isIppatsu(): Boolean
    fun isParent(): Boolean

    override fun compareTo(other: PlayerContext): Int = getJikaze().id - other.getJikaze().id
}

