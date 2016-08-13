/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
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

