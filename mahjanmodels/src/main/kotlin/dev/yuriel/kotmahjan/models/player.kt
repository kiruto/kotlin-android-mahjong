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

open class Player(args: Map<String, Any>) {
    val tehai: Tehai by lazy { Tehai() }
    val furo: Furo by lazy { Furo() }
    val kawa: Kawa by lazy { Kawa() }

    var tsumo: Hai? = null

    val point: Int by args
    val role: Int by args

    fun resetHai() {
        tehai.clear()
        furo.clear()
        kawa.clear()
        tsumo = null
    }

    /*
    fun resetAll() {
        resetHai()
        point = null
    }
    */
}

val masterArgs: Map<String, Any> = mutableMapOf(
        Pair("role", ROLE_MASTER),
        Pair("point", -1)
)
class GameMaster : Player(masterArgs) {

}