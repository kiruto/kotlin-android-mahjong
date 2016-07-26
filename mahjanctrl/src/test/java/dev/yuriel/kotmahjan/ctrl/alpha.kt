package dev.yuriel.kotmahjan.ctrl

import dev.yuriel.kotmahjan.ctrl.analysis.NUM_HAI_ID
import dev.yuriel.kotmahjan.ctrl.impl.AlphaClientDahaiSelector
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.Mentsu
import org.junit.Test
import java.util.*

/**
 * Created by yuriel on 7/26/16.
 */
class AlphaTest {
    private fun selectDahai(tehaisString: String, furos: List<Mentsu>, tsumohaiString: String): Int {
        println(true, "selectDahai", tehaisString, tsumohaiString)
        val result = AlphaClientDahaiSelector.selectDahai(
                readHaiList(tehaisString), furos, Hai.parse(tsumohaiString))
        println("result: $result")
        return result
    }

    @Test
    fun testSelectDahai() {
        assert(12L == selectDahai("m6,m8,p6,p7,p8,p8,p8,p9,s3,s8,s8,s9,N", ArrayList<Mentsu>(), "s3").toLong())
    }

    fun readHaiList(string: String): List<Hai> {
        if (string.isEmpty()) {
            return ArrayList()
        }
        val haiStrings = string.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val haiList = ArrayList<Hai>()
        for (haiString in haiStrings) {
            haiList.add(Hai.parse(haiString))
        }
        return haiList
    }

    fun createRemainingVector(tehais: List<Hai>, otherVisibleHais: List<Hai>): IntArray {
        val remainingVector = IntArray(NUM_HAI_ID)
        Arrays.fill(remainingVector, 4)
        for (tehai in tehais) {
            remainingVector[tehai.id]--
        }
        for (otherVisibleHai in otherVisibleHais) {
            remainingVector[otherVisibleHai.id]--
        }
        return remainingVector
    }

    private fun println(flag: Boolean, vararg o: Any) {
        var s = ""
        for (obj in o) {
            s += obj.toString() + ". "
        }
        println(s)
    }
}