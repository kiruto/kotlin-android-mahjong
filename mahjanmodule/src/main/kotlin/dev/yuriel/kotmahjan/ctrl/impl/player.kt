/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
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

    fun getModel() = model

    override fun onStart() {

    }

    override fun onReceiveHai(hai: Hai) {
        model.tehai.put(hai)
    }

    override fun onHaiPai(haiList: List<Hai>) {
        model.tehai.put(haiList)
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