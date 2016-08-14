/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

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

const val ROLE_MASTER = -1
const val ROLE_OYA = 1 shl 1
const val ROLE_KO = 1 shl 2
const val ROLE_E = 1 shl 3
const val ROLE_S = 1 shl 4
const val ROLE_W = 1 shl 5
const val ROLE_N = 1 shl 6

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
const val STATUS_INVISIBLE = 1 shl 7