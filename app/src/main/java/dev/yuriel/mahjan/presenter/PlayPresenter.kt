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

package dev.yuriel.mahjan.presenter

import dev.yuriel.kotmahjan.ai.I
import dev.yuriel.kotmahjan.ctrl.impl.Kaze
import dev.yuriel.kotmahjan.ctrl.impl.Round
import dev.yuriel.kotmahjan.ctrl.reactive.RoundController
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.PlayerModel
import dev.yuriel.kotmahjan.models.UnbelievableException
import dev.yuriel.mahjan.commander.GamePlayerMgr
import dev.yuriel.mahjan.group.HandsGroup
import dev.yuriel.mahjan.interfaces.Interaction
import dev.yuriel.mahjan.interfaces.PlayViewsInterface
import dev.yuriel.mahjan.model.GamePlayerModel
import dev.yuriel.mahjan.texture.Naki
import dev.yuriel.mahjan.texture.NakiBtn
import rx.Observable
import rx.schedulers.Schedulers
import java.util.concurrent.CountDownLatch

/**
 * Created by yuriel on 8/13/16.
 */
class PlayPresenter(private val view: PlayViewsInterface):
        Interaction, HandsGroup.Listener, NakiBtn.Listener {

    private val ID_LAST = 0x103
    private val ID_KAWA_LISTENER = 0x100
    private val ID_TEHAI_LISTENER = 0x101
    private val ID_TSUMO_LISTENER = 0x102

    private val round = Round()
    private val ctrl = RoundController(round)

    //private val playerA = I("東方不敗")
    private val playerB = I("南夏奈")
    private val playerC = I("西山")
    private val playerD = I("堀北")

    private val playerModel = GamePlayerModel()
    private val player = GamePlayerMgr(this)

    //private val viewer = playerA
    private val viewer = playerModel

    private val tileModels = listOf<PlayerModel>(playerModel, playerB, playerC, playerD)

    @Volatile
    private var touch: Hai? = null

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
        round.addPlayer(tileModels[0], 0, player)
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

    override fun getPlayerModel() = playerModel

    override fun waiting4Flod(): Hai {
        val latch = CountDownLatch(1)
        touch = null
        Observable.just(0)
                .observeOn(Schedulers.newThread())
                .map {
                    while (touch == null){}
                    latch.countDown()
                    touch
                }.subscribe()
        latch.await()
        return touch!!
    }

    override fun waiting4Kan(): Boolean {
        Thread.sleep(5000)
        return false
    }

    override fun waiting4Pon(): Boolean {
        Thread.sleep(5000)
        return false
    }

    override fun waiting4Chi(): Boolean {
        Thread.sleep(5000)
        return false
    }

    override fun waiting4Ron(): Boolean {
        Thread.sleep(5000)
        return false
    }

    override fun onTileTouchDown(hai: Hai) {

    }

    override fun onTileTouchUp(hai: Hai) {
        touch = hai
    }

    override fun onNakiBtnTouchDown(btn: NakiBtn) {

    }

    override fun onNakiBtnTouchUp(btn: NakiBtn) {
        when(btn.naki) {
            Naki.YES -> view.hideNaki()
            Naki.NO -> view.showNaki()
        }
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