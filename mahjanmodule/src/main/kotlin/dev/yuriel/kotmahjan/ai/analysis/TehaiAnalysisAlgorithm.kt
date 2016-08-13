/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.ai.analysis

import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.collections.Mentsu

/**
 * Created by yuriel on 8/13/16.
 */
interface TehaiAnalysisAlgorithm {
    val maxShantensu: Int
    fun processChitoitsu(
            tehais: List<Hai>, toitsuIds: List<Int>, currentVector: IntArray, targetVector: IntArray)

    fun processNormalHora(
            tehais: List<Hai>, furos: List<Mentsu>, mentsuIds: List<Int>, jantoId: Int,
            currentVector: IntArray, targetVector: IntArray)
}
