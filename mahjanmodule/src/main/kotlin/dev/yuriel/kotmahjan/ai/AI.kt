/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.ai

import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.PlayerCommander
import dev.yuriel.kotmahjan.models.fromTypedHaiArray
import dev.yuriel.kotmahjan.models.toTypedHaiArray
import kotlin.properties.Delegates

/**
 * Created by yuriel on 7/30/16.
 */

abstract class AI: PlayerCommander {
    abstract fun store(haiList: List<Hai>)
    abstract fun clear()
    abstract fun getHai(): List<Hai>
    abstract fun getHaiRaw(): String
    abstract fun da(haiList: List<Hai>, basis: List<Hai>): Hai
    abstract fun remove(hai: Hai)

    private var listener: Array<out AISaid>? = null

    protected var msg: String by Delegates.observable("") { prop, old, new -> say(new) }

    override fun da(basis: List<Hai>): Hai {
        return da(getHai(), basis)
    }

    fun setListener(vararg listener: AISaid) {
        this.listener = listener
    }

    protected fun say(str: String) {
        for (l in listener?: return) {
            l.heard(str)
        }
    }

    override fun getBasisByVisibleHai(visible: List<Hai>): List<Hai> {
        val visibleArray = toTypedHaiArray(visible)
        val resultArray = IntArray(34) { i -> 4 - visibleArray[i] }
        return fromTypedHaiArray(resultArray)
    }
}