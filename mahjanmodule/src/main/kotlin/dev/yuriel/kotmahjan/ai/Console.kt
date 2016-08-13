/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.ai

/**
 * Created by yuriel on 8/13/16.
 */
object Console: AISaid {
    override fun heard(str: String) {
        when (str.last()) {
            '\n' -> println(str.substring(0, str.length - 1))
            else -> print(str)
        }
    }
}