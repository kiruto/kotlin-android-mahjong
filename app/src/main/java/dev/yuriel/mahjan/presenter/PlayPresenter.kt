/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.mahjan.presenter

import dev.yuriel.kotmahjan.ai.I
import dev.yuriel.kotmahjan.ctrl.impl.Kaze
import dev.yuriel.kotmahjan.ctrl.impl.Round
import dev.yuriel.kotmahjan.ctrl.reactive.RoundController
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.PlayerModel
import dev.yuriel.kotmahjan.models.UnbelievableException
import dev.yuriel.mahjan.interfaces.PlayViewsInterface
import rx.Observable
import rx.Scheduler
import rx.schedulers.Schedulers

/**
 * Created by yuriel on 8/13/16.
 */
class PlayPresenter(private val view: PlayViewsInterface) {

    private val ID_LAST = 0x103
    private val ID_KAWA_LISTENER = 0x100
    private val ID_TEHAI_LISTENER = 0x101
    private val ID_TSUMO_LISTENER = 0x102

    private val round = Round()
    private val ctrl = RoundController(round)

    private val playerA = I("東方不敗")
    private val playerB = I("南夏奈")
    private val playerC = I("西山")
    private val playerD = I("堀北")

    private val viewer = playerA

    private val tileModels = listOf<PlayerModel>(playerA, playerB, playerC, playerD)


    init {
        for (t in tileModels) {
            t.kawa.listen(ID_KAWA_LISTENER) { list ->
                view.updateKawaFor(indexOf(t), list)
            }

            t.tehai.listen(ID_TEHAI_LISTENER) { list ->
                view.updateTehaiFor(indexOf(t), list)
            }

            t.tsumo.listen(ID_TSUMO_LISTENER) { hai ->
                view.updateTsumoFor(indexOf(t), hai)
            }
        }

        ctrl.listen(ID_LAST) { last ->
            view.updateHaisanLast(last)
        }

        var roundText = ""
        roundText += when(round.kazeRound) {
            0 -> "東"
            1 -> "南"
            2 -> "西"
            3 -> "北"
            else -> throw UnbelievableException()
        }
        roundText += when(round.bakaze) {
            Kaze.EAST -> "一"
            Kaze.SOUTH -> "二"
            Kaze.WEST -> "三"
            Kaze.NORTH -> "四"
        }

        roundText += "局"

        view.updateRoundText(roundText)
    }

    fun addPlayer() {
        round.addPlayer(tileModels[0], 0, playerA)
                .addPlayer(tileModels[1], 1, playerB)
                .addPlayer(tileModels[2], 2, playerC)
                .addPlayer(tileModels[3], 3, playerD)
    }

    fun start() {
        Observable.just(0)
                .observeOn(Schedulers.newThread())
                .map { ctrl.mainLoop() }
                .subscribe()
    }

    private fun roleOf(tileModel: PlayerModel): Kaze {
        return Kaze.values()[tileModels.indexOf(tileModel)]
    }

    private fun indexOf(model: PlayerModel): Int {
        val result = tileModels.indexOf(model) - tileModels.indexOf(viewer)
        if (result >= 0) return result
        return 4 + result
    }
}