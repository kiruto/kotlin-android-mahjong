/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.models

import dev.yuriel.kotmahjan.models.collections.Kawa
import dev.yuriel.kotmahjan.models.collections.Mentsu
import dev.yuriel.kotmahjan.models.collections.Tehai

/**
 * Created by yuriel on 8/13/16.
 */
interface PlayerModel {
    val tehai: Tehai
    val kawa: Kawa
    val mentsu: MutableList<Mentsu>

    var tsumo: TsumoHaiModel

    var point: Int

    fun resetHai() {
        tehai.clear()
        kawa.clear()
        tsumo.hai = null
    }

    /*
    fun resetAll() {
        resetHai()
        point = null
    }
    */
}