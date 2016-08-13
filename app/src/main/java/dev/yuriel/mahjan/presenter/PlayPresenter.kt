package dev.yuriel.mahjan.presenter

import dev.yuriel.kotmahjan.ai.I
import dev.yuriel.kotmahjan.ctrl.impl.Kaze
import dev.yuriel.kotmahjan.ctrl.impl.Round
import dev.yuriel.kotmahjan.ctrl.reactive.RoundController
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.PlayerModel
import dev.yuriel.mahjan.interfaces.PlayViewsInterface

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
    }

    fun addPlayer() {
        round.addPlayer(tileModels[0], 0, playerA)
                .addPlayer(tileModels[1], 1, playerB)
                .addPlayer(tileModels[2], 2, playerC)
                .addPlayer(tileModels[3], 3, playerD)
    }

    fun start() {
        ctrl.mainLoop()
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