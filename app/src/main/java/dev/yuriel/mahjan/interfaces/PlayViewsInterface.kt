package dev.yuriel.mahjan.interfaces

import dev.yuriel.kotmahjan.ctrl.impl.Kaze
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.PlayerModel

/**
 * Created by yuriel on 8/13/16.
 */
interface PlayViewsInterface {
    fun updateKawaFor(position: Int, haiList: List<Hai>)

    fun updateTehaiFor(position: Int, haiList: List<Hai>)

    fun updateTsumoFor(position: Int, hai: Hai?)

    fun updateHaisanLast(last: Int)

    fun updateRoundText(roundText: String)

}