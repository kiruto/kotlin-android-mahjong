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