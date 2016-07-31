/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.ai.analysis

/**
 * Created by yuriel on 7/25/16.
 */
object MentsuUtil {

    private val MANZU_START = 0
    private val PINZU_START = 9
    private val SOZU_START = 18

    private val MANZU_SHUNTSU_START = 0
    private val PINZU_SHUNTSU_START = 7
    private val SOZU_SHUNTSU_START = 14
    private val KOTSU_START = 21
    private val MENTSUS: Array<IntArray>

    init {
        MENTSUS = Array(NUM_MENTSU_ID) { IntArray(3) }
        for (i in MANZU_SHUNTSU_START..PINZU_SHUNTSU_START - 1) {
            MENTSUS[i][0] = i - MANZU_SHUNTSU_START + MANZU_START
            MENTSUS[i][1] = i - MANZU_SHUNTSU_START + MANZU_START + 1
            MENTSUS[i][2] = i - MANZU_SHUNTSU_START + MANZU_START + 2
        }
        for (i in PINZU_SHUNTSU_START..SOZU_SHUNTSU_START - 1) {
            MENTSUS[i][0] = i - PINZU_SHUNTSU_START + PINZU_START
            MENTSUS[i][1] = i - PINZU_SHUNTSU_START + PINZU_START + 1
            MENTSUS[i][2] = i - PINZU_SHUNTSU_START + PINZU_START + 2
        }
        for (i in SOZU_SHUNTSU_START..KOTSU_START - 1) {
            MENTSUS[i][0] = i - SOZU_SHUNTSU_START + SOZU_START
            MENTSUS[i][1] = i - SOZU_SHUNTSU_START + SOZU_START + 1
            MENTSUS[i][2] = i - SOZU_SHUNTSU_START + SOZU_START + 2
        }
        for (i in KOTSU_START..NUM_MENTSU_ID - 1) {
            MENTSUS[i][0] = i - KOTSU_START
            MENTSUS[i][1] = i - KOTSU_START
            MENTSUS[i][2] = i - KOTSU_START
        }
    }

    fun addMentsu(countVector: IntArray, mentsuIndex: Int) {
        countVector[MENTSUS[mentsuIndex][0]]++
        countVector[MENTSUS[mentsuIndex][1]]++
        countVector[MENTSUS[mentsuIndex][2]]++
    }

    fun removeMentsu(countVector: IntArray, mentsuIndex: Int) {
        countVector[MENTSUS[mentsuIndex][0]]--
        countVector[MENTSUS[mentsuIndex][1]]--
        countVector[MENTSUS[mentsuIndex][2]]--
    }

    fun getMentsu(mentsuIndex: Int): IntArray {
        return MENTSUS[mentsuIndex]
    }

    fun isKotsu(mentsuId: Int): Boolean {
        return 21 <= mentsuId && mentsuId < 55
    }

    fun getHaiIdOfKotsu(mentsuId: Int): Int {
        if (!isKotsu(mentsuId)) {
            throw IllegalArgumentException()
        }
        return mentsuId - 21
    }

    fun getMentsuIdOfKotsu(haiId: Int): Int {
        return haiId + 21
    }

    fun isYaochuhai(mentsuId: Int): Boolean {
        return mentsuId == 21 || mentsuId == 29 ||
                mentsuId == 30 || mentsuId == 38 ||
                mentsuId == 39 || mentsuId == 47 ||
                48 <= mentsuId && mentsuId < 55
    }

    fun isSangenpai(mentsuId: Int): Boolean {
        return 52 <= mentsuId && mentsuId < 55
    }

    fun hasYaochuhai(mentsuId: Int): Boolean {
        return mentsuId == 0 || mentsuId == 6 ||
                mentsuId == 7 || mentsuId == 13 ||
                mentsuId == 14 || mentsuId == 20 ||
                isYaochuhai(mentsuId)
    }
}