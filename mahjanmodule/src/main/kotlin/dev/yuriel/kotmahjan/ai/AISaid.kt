/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.ai

/**
 * Created by yuriel on 7/31/16.
 */
interface AISaid {
    fun heard(str: String)
}

object Console: AISaid {
    override fun heard(str: String) {
        when (str.last()) {
            '\n' -> println(str.substring(0, str.length - 1))
            else -> print(str)
        }
    }
}