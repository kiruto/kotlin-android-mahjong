package dev.yuriel.kotmahjan.models

/**
 * Created by yuriel on 7/16/16.
 */

const val MANZU = "m"  //萬子
const val PINZU = "p"  //筒子
const val SOUZU = "s"  //索子
const val FUPAI = "風牌"
const val TOU = "E"   //
const val NAN = "S"   //
const val SHA = "W"   //
const val PEI = "N"   //
const val SANGENPAI = "三元牌"
const val HAKU = "D"  //白
const val HATSU = "H" //発
const val TIUN = "T"  //中

const val STATUS_NORMAL: Int = 1 shl 1
const val STATUS_DIM: Int = 1 shl 2
const val STATUS_HORIZON: Int = 1 shl 3
const val STATUS_NEGATIVE: Int = 1 shl 4

enum class HaiType (val type: String) {
    MZ(MANZU), PZ(PINZU), SZ(SOUZU), E(TOU), S(NAN), W(SHA), N(PEI), D(HAKU), H(HATSU), T(TIUN);
}


class Hai(val type: HaiType, val num: Int = -1): Comparable<Hai> {

    var status: Int = STATUS_NORMAL

    constructor(type: String, num: Int = -1): this(HaiType.valueOf(type), num)

    override fun compareTo(other: Hai): Int = num - other.num

    override fun toString(): String {
        return type.name + if (0 > num && 10 < num) "" else num
    }
}