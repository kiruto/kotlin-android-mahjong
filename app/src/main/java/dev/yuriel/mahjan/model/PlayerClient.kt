package dev.yuriel.mahjan.model

import dev.yuriel.kotmahjan.models.*

/**
 * Created by yuriel on 8/13/16.
 */
class PlayerClient: PlayerModel {

    override val tehai: Tehai = Tehai()
    override val kawa: Kawa = Kawa()
    override val mentsu: MutableList<Mentsu> = mutableListOf()
    override var tsumo: TsumoHaiModel = TsumoHaiModel()

    override var point: Int = 0
}