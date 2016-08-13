/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.models

/**
 * Created by yuriel on 8/13/16.
 */
enum class HaiType (val type: String, val id: Long) {
    MZ(MANZU, 0x1FFL),
    PZ(PINZU, 0x3FE00L),
    SZ(SOUZU, 0x7FC0000L),
    E(TOU, 0x8000000L),
    S(NAN, 0x10000000L),
    W(SHA, 0x20000000L),
    N(PEI, 0x40000000L),
    D(HAKU, 0x80000000L),
    H(HATSU, 0x100000000L),
    T(TIUN, 0x200000000L);
}