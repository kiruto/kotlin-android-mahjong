package dev.yuriel.kotmahjan.models

/**
 * Created by yuriel on 7/20/16.
 */

const val EVENT_INIT = 0x1000

const val EVENT_ROUND_START = 0x0100
const val EVENT_ROUND_END = 0x0200

const val EVENT_LOOP_TSUMO = 0x1000


// 動作
const val ACTION_NONE = 0
const val ACTION_CHI = 0x1
const val ACTION_PON = 0x2
const val ACTION_KAN = 0x3
const val ACTION_RON = 0x4
const val ACTION_CANCEL = 0x5
const val ACTION_SUTE = 0x6
const val ACTION_RICHI = 0x10

// 状態
const val FLAG_NONE = 0L
const val FLAG_CHI = 1L shl 1
const val FLAT_PON = 1L shl 2
const val FLAG_KAN = 1L shl 3
const val FLAG_RON = 1L shl 4

// Extra
const val IS_FIRST_LOOP = "fl;"
const val SHOULD_TSUMO = "st;"
const val SHOULD_TSUMO_AFTER_KAN = "stak;"
const val SHOULD_NOT_TSUMO = "snt;"
const val FROM_RINSHAN = "fr;"

// Console color
val ANSI_RESET = "\u001B[0m"
val ANSI_YELLOW = "\u001B[33m"
val ANSI_CYAN = "\u001B[36m"
val ANSI_RED = "\u001B[31m"