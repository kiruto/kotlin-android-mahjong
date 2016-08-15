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

package dev.yuriel.kotmahjan.ctrl.impl

import dev.yuriel.kotmahjan.models.*
import rx.Observable

/**
 * Created by yuriel on 7/25/16.
 */

open class Player(private val model: PlayerModel,
                  private val jikaze: Kaze,
                  private val commander: PlayerCommander,
                  private val rounder: RoundContextV2): PlayerContext {

    init{

    }

    override fun onStart() {

    }

    override fun onReceiveHai(hai: Hai) {
        model.tehai.put(hai)
    }

    override fun onHaiPai(haiList: List<Hai>) {
        model.tehai.put(haiList)
    }

    override fun afterHaiPai() {
        model.tehai.sort()
    }

    override fun onEnd() {

    }

    override fun onRichi() {

    }

    override fun onChi() {

    }

    override fun onPon() {

    }

    override fun onKan() {

    }

    override fun onRon() {

    }

    override fun onReceive(event: RoundEvent): RoundEventResponse {
        val response: RoundEventResponse = RoundEventResponse()
        if (event.to == model) {
            when(event.action) {
                EVENT_LOOP_TSUMO -> {
                    commander.receive(event.hai!!)

                    val basis = commander.getBasisByVisibleHai(rounder.getAllVisibleHai())
                    response.from = model
                    response.action = ACTION_SUTE
                    response.hai = commander.da(basis)

                    if (model.tsumo.hai == response.hai) {
                        model.tsumo.hai = null
                        Thread.sleep(250L)
                    } else {
                        model.tehai.remove(response.hai!!)
                        Thread.sleep(250L)
                        model.tehai.put(model.tsumo.hai!!)
                        model.tsumo.hai = null
                        model.tehai.sort()
                    }

                    return response
                }
            }
        } else {

        }
        return response
    }

    override fun getObservable(event: RoundEvent, duration: Long): Observable<RoundEvent> {
        return commander.getObservable(event, duration)
    }

    override fun isChankan(): Boolean = false

    override fun isRich(): Boolean = false

    override fun isDoubleRich(): Boolean = false

    override fun isTsumo(): Boolean = false

    override fun getJikaze(): Hai {
        return when(jikaze) {
            Kaze.EAST -> Hai(HaiType.E)
            Kaze.SOUTH -> Hai(HaiType.S)
            Kaze.WEST -> Hai(HaiType.W)
            Kaze.NORTH -> Hai(HaiType.N)
        }
    }

    override fun isRinshankaihoh(): Boolean = false

    override fun isIppatsu(): Boolean = false

    override fun isParent(): Boolean = false
}