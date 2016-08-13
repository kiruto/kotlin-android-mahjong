/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.ctrl

/**
 * Created by yuriel on 7/20/16.
 */
class Flags {
    var flags: Long = 0L

    fun add(flag: Long): Long {
        flags = flags and flag
        return flags
    }

    fun remove(flag: Long): Long {
        flags = flags and flag.inv()
        return flags
    }

    fun has(flag: Long): Boolean {
        return flags and flag != 0L
    }

    fun reset() {
        flags = 0L
    }
}