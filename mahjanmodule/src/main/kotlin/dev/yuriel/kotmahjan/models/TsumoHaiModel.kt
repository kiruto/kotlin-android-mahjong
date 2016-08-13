/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.models

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import kotlin.properties.Delegates

/**
 * Created by yuriel on 8/13/16.
 */
class TsumoHaiModel {

    private val listeners = mutableMapOf<Int, (Hai?) -> Unit>()
    var hai: Hai? by Delegates.observable<Hai?>(null) { prop, old, new ->
        notifyDataChange(new)
    }

    fun listen(id: Int, listener: (Hai?) -> Unit) {
        listeners.put(id, listener)
    }

    private fun notifyDataChange(h: Hai?) {
        Observable.just(0)
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    for ((id, l) in listeners) {
                        l.invoke(h)
                    }
                }
                .subscribe()
    }
}