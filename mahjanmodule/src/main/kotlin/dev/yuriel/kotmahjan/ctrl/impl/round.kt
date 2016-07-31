/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.ctrl.impl

import dev.yuriel.kotmahjan.ctrl.HaiMgr
import dev.yuriel.kotmahjan.models.*

/**
 * Created by yuriel on 7/25/16.
 * 局の神クラス
 * 局に関することにはなんでもできます。
 */

open class Round: RoundContextV2 {

    private val players: Array<PlayerContext?>
    private val playerModels: Array<PlayerModel?>
    private val playerMap: MutableMap<PlayerModel, Int>
    private var haiMgr: HaiMgr
    private var roundNo: Int
    private var nextRound: Boolean = true


    override var isFirstLoop: Boolean = false
    override var isRich: Boolean = false

    init {
        players = Array<PlayerContext?>(4) { it -> null }
        playerModels = Array<PlayerModel?>(4) { it -> null }
        playerMap = mutableMapOf<PlayerModel, Int>()
        haiMgr = HaiMgr()
        roundNo = 0
    }

    override fun getPlayerContext(player: PlayerModel): PlayerContext = players[playerMap[player]!!]!!

    override fun getAllVisibleHai(): List<Hai> {
        val result = mutableListOf<Hai>()
        result.addAll(haiMgr.doraOmote())
        for (m in playerModels) {
            result.addAll(m?.kawa?.get()?: continue)
        }
        return result
    }

    override fun onStart() {
        for (p in players) if (null == p) throw PlayerNotReadyException()
        haiMgr = HaiMgr()
        isFirstLoop = false
        isRich = false
        roundNo ++
    }

    override fun onHaiPai() {

    }

    override fun onStop() {
        nextRound = false
    }

    override fun hasNextRound(): Boolean {
        return nextRound
    }

    override fun onEnd() {

    }

    override fun onReceiveEvent(event: RoundEvent) {
        println("event: from:${event.from}, to: ${event.to}, hai: ${event.hai}, action: ${event.action}")
    }

    override fun getPlayerList(): List<PlayerModel> {
        return listOf(playerModels[0]!!, playerModels[1]!!, playerModels[2]!! ,playerModels[3]!!)
    }

    override fun getBakaze(): Hai {
        return when (roundNo % 4) {
            1 -> Hai(HaiType.E)
            2 -> Hai(HaiType.S)
            3 -> Hai(HaiType.W)
            4 -> Hai(HaiType.N)
            else -> throw UnbelievableException()
        }
    }

    override fun isHoutei(): Boolean = !haiMgr.hasHai()

    override fun getDora(): List<Hai> = haiMgr.doraOmote()

    override fun getUradora(): List<Hai> = haiMgr.doraUra()

    fun addPlayer(p: PlayerModel, jikaze: Kaze, c: PlayerCommander): Round {
        val context = Player(p, jikaze, c, this)
        players[jikaze.index] = context
        playerModels[jikaze.index] = p
        playerMap.put(p, jikaze.index)
        return this
    }

    @Throws(IllegalIntKazeException::class)
    fun addPlayer(p: PlayerModel, jikaze: Int, c: PlayerCommander): Round {
        if (jikaze < 0 || jikaze > 3) {
            throw IllegalIntKazeException(jikaze)
        }
        return addPlayer(p, Kaze.values()[jikaze], c)
    }
}