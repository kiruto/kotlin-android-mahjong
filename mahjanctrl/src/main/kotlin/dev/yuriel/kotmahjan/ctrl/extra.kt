package dev.yuriel.kotmahjan.ctrl

import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.Player

/**
 * Created by yuriel on 7/17/16.
 */

fun MutableList<Hai>.pop4(): List<Hai>{
    val result = mutableListOf<Hai>()
    for (i in size - 4..size) {
        result.add(this[i])
        removeAt(i)
    }
    return result
}

fun Player.getHaiOnStartRound() {

}
