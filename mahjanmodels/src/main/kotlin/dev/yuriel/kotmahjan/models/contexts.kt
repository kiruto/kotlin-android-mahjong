package dev.yuriel.kotmahjan.models

import rx.Observable

/**
 * Created by yuriel on 7/23/16.
 */
interface RoundContext {
    /**
     * 対局開始
     */
    fun onStart()

    /**
     * 配牌
     */
    fun onHaiPai()

    fun isEndOfRound(): Boolean

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
    fun getPlayerList(): List<Player>

    fun getBakaze(): Hai

    fun isRich(): Boolean

    fun isHoutei(): Boolean
}

interface RoundContextV1: RoundContext {
    /**
     * 巡
     */
    fun onLoop(player: Player, loopContext: LoopContext)
    fun getLoopContext(player: Player): LoopContext
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
    fun getPlayerContext(player: Player): PlayerContext
}

interface PlayerContext {
    fun onStart()
    fun onReceiveHai(hai: Hai)
    fun onHaiPai(haiList: List<Hai>)
    fun onEnd()

    fun onRichi()
    fun onChi()
    fun onPon()
    fun onKan()
    fun onRon()

    //fun could(): Flags
    fun onReceive(event: RoundEvent): RoundEventResponse
    //fun getSubscriber(): Subscriber<RoundEvent>
    fun getObservable(event: RoundEvent, duration: Long): Observable<RoundEvent>?
    //fun getPlayer(): Player
    fun isChankan(): Boolean

    fun  isRich(): Boolean

    fun isDoubleRich(): Boolean

    fun isTsumo(): Boolean

    fun getJikaze(): Hai

    fun  isRinshankaihoh(): Boolean

    fun  isIppatsu(): Boolean

}

