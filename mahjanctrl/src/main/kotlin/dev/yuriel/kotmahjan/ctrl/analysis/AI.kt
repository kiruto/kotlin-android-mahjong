package dev.yuriel.kotmahjan.ctrl.analysis

import dev.yuriel.kotmahjan.models.Hai

/**
 * Created by yuriel on 7/30/16.
 */

abstract class AI {
    abstract fun store(haiList: List<Hai>)
    abstract fun clear()
    abstract fun getHai(): List<Hai>
    abstract fun da(haiList: List<Hai>, basis: List<Hai>): Hai
    abstract fun receive(hai: Hai)
    abstract fun remove(hai: Hai)

    fun da(basis: List<Hai>): Hai {
        val result = da(getHai(), basis)
        remove(result)
        return result
    }
}