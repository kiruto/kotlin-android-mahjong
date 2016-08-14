/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.models.collections

import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.toTypedHaiArray
import rx.Observable
import rx.android.schedulers.AndroidSchedulers

/**
 * Created by yuriel on 8/13/16.
 */
abstract class TileCollection {

    protected val listeners = mutableMapOf<Int, (List<Hai>) -> Unit>()
    protected abstract val haiListStore: MutableList<Hai>
    val haiList: List<Hai> by lazy { haiListStore }

    fun listen(id: Int, listener: (List<Hai>) -> Unit) {
        listeners.put(id, listener)
    }

    protected fun notifyDataChange() {
        Observable.just(0)
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    for ((id, l) in listeners) {
                        l.invoke(haiList)
                    }
                }
                .subscribe()
    }

    fun remove(hai: Hai) {
        for (h in haiList) {
            if (h == hai) {
                haiListStore.remove(hai)
                notifyDataChange()
                return
            }
        }
    }

    /**
     * [0,0,0,0,0,0,0,0,0,  MZ
     *  0,0,0,0,0,0,0,0,0,  PZ
     *  0,0,0,0,0,0,0,0,0,  SZ
     *  0,0,0,0,0,0,0]      TSUHAI
     */
    fun toTypedArray(removeFuro:Boolean = true): IntArray = toTypedHaiArray(haiList, removeFuro)
}