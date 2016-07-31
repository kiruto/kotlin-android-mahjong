/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.ctrl

import dev.yuriel.kotmahjan.ctrl.impl.AlphaClientDahaiSelector
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.Mentsu
import org.junit.Test
import java.util.*

/**
 * Created by yuriel on 7/26/16.
 */
@Deprecated("")
class AlphaTest {
    private fun selectDahai(tehaisString: String, furos: List<Mentsu>, tsumohaiString: String): Int {
        println(true, "selectDahai", tehaisString, tsumohaiString)
        val result = AlphaClientDahaiSelector.selectDahai(
                readHaiList(tehaisString), furos, Hai.parse(tsumohaiString))
        println("result: $result")
        return result
    }

    //@Test
    fun testSelectDahai() {
        assert(12L == selectDahai("m6,m8,p6,p7,p8,p8,p8,p9,s3,s8,s8,s9,N", ArrayList<Mentsu>(), "s3").toLong())
    }
}