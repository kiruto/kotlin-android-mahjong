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
    //private var haiMgr: HaiMgr
    private var roundNo: Int
    private var nextRound: Boolean = true

    val bakaze: Kaze
        get() {
            return when (roundNo % 4) {
                0 -> Kaze.EAST
                1 -> Kaze.SOUTH
                2 -> Kaze.WEST
                3 -> Kaze.NORTH
                else -> throw UnbelievableException()
            }
        }

    val kazeRound: Int
        get() {
            return roundNo / 4
        }

    override var isFirstLoop: Boolean = false
    override var isRich: Boolean = false
    override var isHoutei: Boolean = false
    override var dora: List<Hai> = listOf()
    override var uradora: List<Hai> = listOf()

    init {
        players = Array<PlayerContext?>(4) { it -> null }
        playerModels = Array<PlayerModel?>(4) { it -> null }
        playerMap = mutableMapOf<PlayerModel, Int>()
        //haiMgr = HaiMgr()
        roundNo = 0
    }

    override fun getPlayerContext(player: PlayerModel): PlayerContext = players[playerMap[player]!!]!!

    override fun getAllVisibleHai(): List<Hai> {
        val result = mutableListOf<Hai>()
        result.addAll(dora)
        for (m in playerModels) {
            result.addAll(m?.kawa?.get()?: continue)
        }
        return result
    }

    override fun onStart(haiMgr: HaiMgr) {
        for (p in players) if (null == p) throw PlayerNotReadyException()
        //this.haiMgr = HaiMgr()
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
            0 -> Hai(HaiType.E)
            1 -> Hai(HaiType.S)
            2 -> Hai(HaiType.W)
            3 -> Hai(HaiType.N)
            else -> throw UnbelievableException()
        }
    }

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