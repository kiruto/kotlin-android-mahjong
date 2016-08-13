/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.models

import dev.yuriel.kotmahjan.ctrl.HaiMgr

/**
 * Created by yuriel on 8/13/16.
 */
interface RoundContext {
    var isFirstLoop: Boolean
    var isRich: Boolean
    /**
     * 対局開始
     */
    fun onStart(haiMgr: HaiMgr)

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
    var isHoutei: Boolean
    var dora: List<Hai>
    var uradora: List<Hai>

}