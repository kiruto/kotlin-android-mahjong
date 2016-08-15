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

package dev.yuriel.kotmahjan.ctrl.reactive

import dev.yuriel.kotmahjan.ctrl.Flags
import dev.yuriel.kotmahjan.ctrl.HaiMgr
import dev.yuriel.kotmahjan.models.*
import rx.Observable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.internal.schedulers.NewThreadScheduler
import rx.schedulers.Schedulers
import java.util.concurrent.CountDownLatch
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * Created by yuriel on 7/20/16.
 * 开始
 * 发牌
 * 循环（没拍可摸？）
 * 结束
 */
class RoundController(val rounder: RoundContextV2) {
    private var haiMgr: HaiMgr? = null

    private var log: RoundEvent by Delegates.observable(RoundEvent()) {
        prop, old, new -> rounder.onReceiveEvent(new)
    }

    private var roundResult: List<RoundEvent> by Delegates.observable(listOf()) {
        prop, old, new -> onResult(prop, old, new)
    }

    private var roundEvent: RoundEvent by Delegates.observable(RoundEvent()) {
        prop, old, new -> onEvent(prop, old, new)
    }

    fun listen(id: Int, listener: (Int) -> Unit) {
        haiMgr?.listen(id, listener)
    }

    // 結果発表
    private fun onResult(prop: KProperty<*>, old: List<RoundEvent>, new: List<RoundEvent>) {
        if (new.size < 3) {
            for (e in new) {
                if (e.action == ACTION_RON) {
                    rounder.getPlayerContext(e.to).onRon()
                }
            }
        } else {

        }
    }

    private fun onEvent(prop: KProperty<*>, old: RoundEvent, new: RoundEvent) {
        when (new.action) {
            ACTION_PON -> rounder.getPlayerContext(new.to).onPon()
            ACTION_KAN -> rounder.getPlayerContext(new.to).onKan()
            ACTION_CHI -> rounder.getPlayerContext(new.to).onChi()
            ACTION_RICHI -> {
                rounder.isRich = true
                rounder.getPlayerContext(new.to).onRichi()
            }
        }
    }

    @Throws(UnexpectedActionExcept::class)
    fun mainLoop() {
        reset()
        while (rounder.hasNextRound()) {
            rounder.onStart(haiMgr!!)
            rounder.dora = haiMgr?.doraOmote()?: listOf()
            rounder.uradora = haiMgr?.doraUra()?: listOf()
            rounder.onHaiPai()
            // 配牌
            for (i in 0..2)
                for (p in rounder.getPlayerList()) {
                    Thread.sleep(200L)
                    rounder.getPlayerContext(p).onHaiPai(getHaiPai())
                }

            // 配牌最后一张
            for (p in rounder.getPlayerList()) {
                Thread.sleep(200L)
                rounder.getPlayerContext(p).onReceiveHai(getHai())
            }
            rounder.isFirstLoop = true

            var loop = 0
            var targetPlayer = rounder.getPlayerList()[0]
            var tsumoType: String = SHOULD_TSUMO

            for (p in rounder.getPlayerList()) {
                rounder.getPlayerContext(p).afterHaiPai()
            }

            // 第一巡开始
            round@while (hasHai()) {
                rounder.isHoutei = !hasHai()
                loop ++
                if (loop > 34) return
                prl("巡: $loop")
                

                loop@for (player in rounder.getPlayerList()) {
                    // todo 四风连打
                    if (targetPlayer != player) continue
                    val events = looper(rounder, player, rounder.isFirstLoop, tsumoType)
                    if (events.size > 1) {
                        // 一炮多响
                        rounder.onStop()
                        return
                    }
                    if (events.isEmpty()) {
                        targetPlayer = rounder.getPlayerList().startOf(targetPlayer)[1]
                        continue
                    }
                    val event = events[0]

                    if (event.action == ACTION_RON) {
                        rounder.onStop()
                        break@round
                    } else if (event.action == ACTION_KAN) {
                        rounder.isFirstLoop = false
                        targetPlayer = event.from
                        tsumoType = SHOULD_TSUMO_AFTER_KAN
                        break@loop
                    } else if (event.action == ACTION_PON) {
                        rounder.isFirstLoop = false
                        targetPlayer = event.from
                        tsumoType = SHOULD_NOT_TSUMO
                        break@loop
                    } else if (event.action == ACTION_CHI) {
                        rounder.isFirstLoop = false
                        targetPlayer = event.from
                        tsumoType = SHOULD_NOT_TSUMO
                        break@loop
                    } else if (event.action != ACTION_NONE) {
                        rounder.isFirstLoop = false
                        targetPlayer = rounder.getPlayerList().startOf(targetPlayer)[1]
                        // todo
                    } else {
                        throw UnexpectedActionExcept(event.action)
                    }

                }
                rounder.isFirstLoop = false
            }
            rounder.onStop()
        }
        rounder.onEnd()
    }

    /**
     * 循环（无差别）：
     * 条件：是否第一巡，player
     * －－－－－
     *     摸排
     *     action：和？杠？（跳过第一寻）立？
     *         第一巡：九种九牌？
     *     循环：暗杠（跳过第一巡） － 摸排 － action
     *     弃牌
     *     询问（共享时间，平等决策，先后顺序）：
     *         顺序 和？
     *         碰／杠？
     *         顺序 吃？
     *         （有动作：跳过第一巡）
     *         （有动作：改变下家）
     *     return 下家
     *
     * 第一巡：四风连打？
     */
    private fun looper(context: RoundContextV2, player: PlayerModel,
                       isFirstLoop: Boolean, tsumoType: String): List<RoundEvent> {
        val pContext = context.getPlayerContext(player)
        val result: MutableList<RoundEvent> = mutableListOf()
        if (!hasHai()) {
            // todo
            return result
        }

        val hai: Hai?
        if (tsumoType == SHOULD_TSUMO) {
            hai = getHai()
            prl("配牌: $hai")
        } else if (tsumoType == SHOULD_TSUMO_AFTER_KAN) {
            hai = getHaiAfterKan()
        } else {
            hai = null
        }
        pContext.onStart()
        val firstLoop = if(isFirstLoop) IS_FIRST_LOOP else ""
        var event = pContext.onReceive(RoundEvent(to = player, action = EVENT_LOOP_TSUMO,
                hai = hai, extra = firstLoop))
        var rinshan = ""
        while (true) {
            if (event.action == ACTION_RON) {
                // ツモ　嶺上開花
                result.add(RoundEvent(action = ACTION_RON, to = player, hai = hai, extra = rinshan))
                roundResult = result
                return result
            } else if (event.action == ACTION_KAN) {
                val haiKan = getHaiAfterKan()
                roundEvent = event
                event = pContext.onReceive(RoundEvent(to = player, action = EVENT_LOOP_TSUMO, hai = haiKan))
                rinshan = FROM_RINSHAN
            } else break
        }
        if (event.action == ACTION_SUTE) {
            player.kawa.push(event.hai!!)
            result.addAll(askOtherPlayersFor(event, context, player))
        } else if (event.action == ACTION_RICHI) {
            player.kawa.push(event.hai!!)
            result.addAll(askOtherPlayersFor(event, context, player))
            roundEvent = event.change()
        } else {
            throw MahjanException("can not newInstance Hai for event: ${event.toString()}")
        }
        pContext.onEnd()
        return result
    }

    private fun askOtherPlayersFor(event: RoundEvent, context: RoundContextV2,
                                   player: PlayerModel, duration: Long = 5000): List<RoundEvent> {
        val resultEvent: MutableList<RoundEvent> = mutableListOf()
        val litch = CountDownLatch(1)
        val players: List<PlayerModel> = context.getPlayerList().startOf(player)
        val actions = object {
            private val size = players.size - 1
            val value = mutableMapOf<PlayerModel, RoundEvent>()

            fun add(event: RoundEvent) {
                value.put(event.from, event)
            }

            fun remove(player: PlayerModel) {
                value.remove(player)
            }

            operator fun get(player: PlayerModel): RoundEvent? {
                return value[player]
            }

            fun isEnd(): Boolean {
                return value.size >= size
            }
        }
        val observer = object: Observer<RoundEvent> {
            override fun onNext(e: RoundEvent) {
                actions.add(e)
                if (actions.isEnd()) {
                    var result: Boolean = false

                    // 和了
                    for ((p, v) in actions.value) {
                        if (v.action == ACTION_NONE) {
                            actions.remove(p)
                        }
                        if (v.action == ACTION_RON) {
                            rounder.getPlayerContext(p).onRon()
                            resultEvent.add(RoundEvent(to = p, action = ACTION_RON, hai = v.hai))
                            result = true
                        }
                    }
                    if (result) {
                        roundResult = resultEvent
                        actions.value.clear()
                        return
                    }

                    // 碰
                    for ((p, v) in actions.value) {
                        if (actions[p]?.action == ACTION_PON){
                            resultEvent.add(RoundEvent(to = p, action = ACTION_PON, hai = v.hai))
                            result = true
                            break
                        } else if (actions[p]?.action == ACTION_KAN) {
                            resultEvent.add(RoundEvent(to = p, action = ACTION_KAN, hai = v.hai))
                            result = true
                            break
                        }
                    }
                    if (result) actions.value.clear()

                    for (p in players) {
                        if (actions[p]?.action == ACTION_CHI) {
                            resultEvent.add(RoundEvent(to = p, action = ACTION_CHI, hai = actions[p]?.hai))
                            result = true
                            break
                        }
                    }
                    if (!result) resultEvent.add(RoundEvent(action = ACTION_NONE))
                    roundEvent = resultEvent[0]
                }
            }

            override fun onCompleted() {
                litch.countDown()
            }

            override fun onError(e: Throwable) {
                e.stackTrace
            }

        }
        for (p in players) {
            if (p == player) continue
            val pContext = context.getPlayerContext(player)
            pContext.getObservable(event, duration)
                    ?.observeOn(Schedulers.newThread())
                    ?.subscribe(observer)
        }

        litch.await()
        return resultEvent
    }

    private fun getHaiPai(): List<Hai> = haiMgr!!.haiPai()

    private fun hasHai(): Boolean = haiMgr?.hasHai()?:false

    private fun getHai(): Hai = haiMgr!!.getHai()

    private fun couldKan(): Boolean = haiMgr!!.couldKan()

    private fun getHaiAfterKan(): Hai {
        val hai = haiMgr!!.kan()
        rounder.dora = haiMgr?.doraOmote()?: listOf()
        rounder.uradora = haiMgr?.doraUra()?: listOf()
        return hai
    }

    private fun reset() {
        haiMgr = HaiMgr()
    }

    fun <T> List<T>.startOf(t: T): List<T> {
        if (t !in this) return listOf()
        val result: MutableList<T> = mutableListOf()
        result.addAll(this)
        val startAt = indexOf(t)
        if (startAt == 0) return result
        for (i in 0..startAt - 1) {
            val obj = result[0]
            result.removeAt(0)
            result.add(obj)
        }
        return result
    }

    private fun pr(str: String) {
        print(ANSI_RED + str + ANSI_RESET)
    }

    private fun prl(str: String) {
        println(ANSI_RED + str + ANSI_RESET)
    }
}
