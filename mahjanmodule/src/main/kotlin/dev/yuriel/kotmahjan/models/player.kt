/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.models

/**
 * Created by yuriel on 7/16/16.
 */

const val ROLE_MASTER = -1
const val ROLE_OYA = 1 shl 1
const val ROLE_KO = 1 shl 2
const val ROLE_E = 1 shl 3
const val ROLE_S = 1 shl 4
const val ROLE_W = 1 shl 5
const val ROLE_N = 1 shl 6

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

class GameMaster : PlayerModel {
    override val tehai: Tehai = Tehai()
    override val kawa: Kawa = Kawa()
    override val mentsu: MutableList<Mentsu> = mutableListOf()
    override var tsumo: TsumoHaiModel = TsumoHaiModel()
    override var point: Int = -1
}