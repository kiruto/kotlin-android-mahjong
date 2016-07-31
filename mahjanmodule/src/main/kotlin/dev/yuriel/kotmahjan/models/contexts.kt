/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.models

import rx.Observable

/**
 * Created by yuriel on 7/23/16.
 */
interface RoundContext {
    var isFirstLoop: Boolean
    var isRich: Boolean
    /**
     * 対局開始
     */
    fun onStart()

    /**
     * 配牌
     */
    fun onHaiPai()

    /**
     * 対局終了
     */
    fun onStop()

    fun hasNextRound(): Boolean

    /**
     * すべて終了
     */
    fun onEnd()

    fun onReceiveEvent(event: RoundEvent)

    /**
     * 親から東南西北順
     */
    fun getPlayerList(): List<PlayerModel>

    fun getBakaze(): Hai
    fun isHoutei(): Boolean
    fun getDora(): List<Hai>
    fun getUradora(): List<Hai>

}

interface RoundContextV1: RoundContext {
    /**
     * 巡
     */
    fun onLoop(player: PlayerModel, loopContext: LoopContext)
    fun getLoopContext(player: PlayerModel): LoopContext
}

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

interface RoundContextV2: RoundContext {
    fun getPlayerContext(player: PlayerModel): PlayerContext
    fun getAllVisibleHai(): List<Hai>
}

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

