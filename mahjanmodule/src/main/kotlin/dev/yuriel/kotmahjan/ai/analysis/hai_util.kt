/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.ai.analysis

import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.collections.Mentsu

/**
 * Created by yuriel on 7/25/16.
 */
fun isYaochuhai(haiId: Int): Boolean {
    //val haiId = id - 1
    return haiId == 0 || haiId == 8 ||
            haiId == 9 || haiId == 17 ||
            haiId == 18 || haiId == 26 ||
            27 <= haiId && haiId < 34
}

fun isSangenpai(id: Int): Boolean {
    return 31 <= id && id < 34
}

fun haiListToCountVector(haiList: List<Hai>): IntArray {
    val countVector = IntArray(NUM_HAI_ID)
    for (hai in haiList) {
        countVector[hai.idFZ]++
    }
    return countVector
}

fun getDistance(currentVector: IntArray, targetVector: IntArray): Int {
    var count = 0
    for (haiId in 0..NUM_HAI_ID - 1) {
        if (targetVector[haiId] > currentVector[haiId]) {
            count += targetVector[haiId] - currentVector[haiId]
        }
    }
    return count
}

fun calculateShantensu(tehais: List<Hai>, furoCount: Int = 0): Int {
    val currentVector = haiListToCountVector(tehais)
    return ShantensuCalculatorInternal(currentVector, furoCount).calculateShantensu()
}

fun calculateShantensu(tehais: List<Hai>, furos: List<Mentsu> = listOf()): Int = calculateShantensu(tehais, furos.size)

fun runAlgorithm(
        tehais: List<Hai>, furos: List<Mentsu>, algorithm: TehaiAnalysisAlgorithm) {
    AlgorithmRunnerInternal(tehais, furos, algorithm).runAlgorithm()
}