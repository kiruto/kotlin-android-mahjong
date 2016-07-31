/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.ai

import dev.yuriel.kotmahjan.models.Hai
import kotlin.properties.Delegates

/**
 * Created by yuriel on 7/30/16.
 */

abstract class AI {
    abstract fun store(haiList: List<Hai>)
    abstract fun clear()
    abstract fun getHai(): List<Hai>
    abstract fun getHaiRaw(): String
    abstract fun da(haiList: List<Hai>, basis: List<Hai>): Hai
    abstract fun receive(hai: Hai)
    abstract fun remove(hai: Hai)

    private var listener: Array<out AISaid>? = null

    protected var msg: String by Delegates.observable("") { prop, old, new -> say(new) }

    fun da(basis: List<Hai>): Hai {
        val result = da(getHai(), basis)
        remove(result)
        return result
    }

    fun setListener(vararg listener: AISaid) {
        this.listener = listener
    }

    protected fun say(str: String) {
        for (l in listener?: return) {
            l.heard(str)
        }
    }
}