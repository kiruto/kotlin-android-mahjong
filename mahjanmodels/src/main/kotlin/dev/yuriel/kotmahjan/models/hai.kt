package dev.yuriel.kotmahjan.models

import java.util.*

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

const val STATUS_NORMAL = 1 shl 1
const val STATUS_DIM = 1 shl 2
const val STATUS_HORIZON = 1 shl 3
const val STATUS_NEGATIVE = 1 shl 4
const val STATUS_DORA = 1 shl 5
const val STATUS_FURO = 1 shl 6

fun List<Hai>.isShun(): Boolean {
    if (size != 3 || this[0].type != this[1].type || this[1].type != this[2].type) return false
    val list = Collections.sort(this)
    return this[0].num == this[1].num - 1 && this[1].num == this[2].num - 1
}

fun List<Hai>.isKo(): Boolean = size == 3 && this[0].sameAs(this[1]) && this[1].sameAs(this[2])

fun List<Hai>.isKan(): Boolean = size == 4 && this[0].sameAs(this[1]) && this[1].sameAs(this[2]) && this[2].sameAs(this[3])

fun List<Hai>.isDoi(): Boolean = size == 2 &&  this[0].sameAs(this[1])

fun List<Hai>.isGroup(): Boolean = isShun() || isKo() || isKan() || isDoi()

fun isShun(vararg hai: Hai): Boolean = hai.toList().isShun()

fun isKo(vararg hai: Hai): Boolean = hai.toList().isKo()

fun isKan(vararg hai: Hai): Boolean = hai.toList().isKan()

fun isDoi(vararg hai: Hai): Boolean = hai.toList().isDoi()

fun isGroup(vararg hai: Hai): Boolean = hai.toList().isGroup()


enum class HaiType (val type: String, val id: Long) {
    MZ(MANZU, 0x1FFL),
    PZ(PINZU, 0x3FE00L),
    SZ(SOUZU, 0x7FC0000L),
    E(TOU, 0x8000000L),
    S(NAN, 0x10000000L),
    W(SHA, 0x20000000L),
    N(PEI, 0x40000000L),
    D(HAKU, 0x80000000L),
    H(HATSU, 0x100000000L),
    T(TIUN, 0x200000000L);
}

val TSUHAI_ID = 0x3f8000000L
val SANGEN_ID = 0x380000000L


class Hai(val type: HaiType, val num: Int = -1, var status: Int = STATUS_NORMAL): Comparable<Hai> {

    val id: Int by lazy { (Math.log((if (-1 != num) 0x3f8040201L and type.id else type.id).toDouble()) / Math.log(2.0)).toInt() }
    constructor(type: String, num: Int = -1): this(HaiType.valueOf(type), num)

    infix fun sameAs(hai: Hai): Boolean = type == hai.type && num == hai.num

    fun hasStatus(flag: Int): Boolean {
        return status and flag == flag
    }

    fun removeStatus(flag: Int) {
        status = status and flag.inv()
    }

    fun addStatus(flag: Int) {
        status = status or flag
    }

    fun isYaochu(): Boolean = num == 1 || num == 9 || num == -1

    fun getCode(): Int {
        return id
    }

    override fun compareTo(other: Hai): Int = num - other.num

    override fun toString(): String {
        return type.name + if (0 > num && 10 < num) "" else num
    }

    companion object factory {
        fun newInstance(id: Int):Hai {
            when (id) {
                27 -> return Hai(HaiType.E)
                28 -> return Hai(HaiType.S)
                29 -> return Hai(HaiType.W)
                30 -> return Hai(HaiType.N)
                31 -> return Hai(HaiType.D)
                32 -> return Hai(HaiType.H)
                33 -> return Hai(HaiType.T)
            }
            if (0 <= id && 9 > id) {
                return Hai(HaiType.MZ, id)
            } else if (9 <= id && 18 > id) {
                return Hai(HaiType.PZ, id - 9)
            } else if (18 <= id && 27 > id) {
                return Hai(HaiType.SZ, id - 18)
            } else {
                throw RuntimeException()
            }
        }
    }
}