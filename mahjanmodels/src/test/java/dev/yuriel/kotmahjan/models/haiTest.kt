package dev.yuriel.kotmahjan.models

import org.junit.Test

/**
 * Created by yuriel on 7/26/16.
 */
class HaiTest {
    val haiStrings = mutableListOf<String>()
    fun initStringArray() {
        haiStrings.clear()
        for (i in 0..2) {
            val s: String =
            when(i) {
                0 -> "m"
                1 -> "p"
                2 -> "s"
                else -> ""
            }
            for (j in 1..9) {
                if (j == 5) {
                    haiStrings.add(s + "+" + j)
                } else {
                    haiStrings.add(s + j)
                }
            }
        }
        haiStrings.add("E")
        haiStrings.add("S")
        haiStrings.add("W")
        haiStrings.add("N")
        haiStrings.add("D")
        haiStrings.add("H")
        haiStrings.add("T")
    }

    @Test
    fun testHaiFactory() {
        initStringArray()
        for (s in haiStrings) {
            val h = Hai.parse(s)
            println("index: ${haiStrings.indexOf(s)}, $s, id:${h.id}, $h")
            assert(haiStrings.indexOf(s) == h.id - 1)
            assert(s.equals(h.toString()))
        }
    }
}