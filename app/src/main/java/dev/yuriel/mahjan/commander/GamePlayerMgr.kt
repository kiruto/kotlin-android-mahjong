/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.mahjan.commander

import android.util.Log
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.PlayerCommander
import dev.yuriel.kotmahjan.models.RoundEvent
import dev.yuriel.mahjan.interfaces.Interaction
import rx.Observable

/**
 * Created by yuriel on 8/14/16.
 */
class GamePlayerMgr(private val presenter: Interaction): PlayerCommander {

    override fun receive(hai: Hai) {
        presenter.getPlayerModel().tsumo.hai = hai
    }

    override fun da(basis: List<Hai>): Hai {
        return presenter.waiting4Flod()
    }

    override fun kan(haiList: List<Hai>): Boolean {
        return presenter.waiting4Kan()
    }

    override fun pon(haiList: List<Hai>): Boolean {
        return presenter.waiting4Pon()
    }

    override fun chi(haiList: List<Hai>): Boolean {
        return presenter.waiting4Chi()
    }

    override fun ron(haiList: List<Hai>): Boolean {
        return presenter.waiting4Ron()
    }

    override fun getBasisByVisibleHai(visible: List<Hai>): List<Hai> {
        return listOf()
    }

    override fun getObservable(event: RoundEvent, duration: Long): Observable<RoundEvent> {
        return Observable.just(event).map { e ->
            Log.d(javaClass.simpleName, e.toString())
            e
        }
    }
}