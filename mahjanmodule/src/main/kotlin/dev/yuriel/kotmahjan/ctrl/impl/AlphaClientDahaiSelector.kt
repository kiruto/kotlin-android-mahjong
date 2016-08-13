/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.ctrl.impl

import dev.yuriel.kotmahjan.ai.analysis.*
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.collections.Mentsu
import java.util.*

/**
 * Created by yuriel on 7/26/16.
 */
object AlphaClientDahaiSelector {

    fun selectDahai(tehais: List<Hai>, furos: List<Mentsu>, tsumohai: Hai?): Int {
        val tehaisWithTsumohai = ArrayList<Hai>(tehais)

        // To handle a dahai after a pon/chi.
        if (tsumohai != null) {
            tehaisWithTsumohai.add(tsumohai)
        }

        val minShantensu = ShantensuCalculatorInternal(haiListToCountVector(tehais), furos.size).calculateShantensu()
        val evaluationResults = evaluateDahai(
                tehaisWithTsumohai, furos, minShantensu)
        var bestIndex = 0
        var bestResult = evaluationResults[0]
        for (index in 1..tehaisWithTsumohai.size - 1) {
            if (evaluationResults[index] != null && bestResult != null && bestResult.compareTo(evaluationResults[index]!!) < 0) {
                bestIndex = index
                bestResult = evaluationResults[index]
            }
        }
        if (bestIndex < tehais.size) {
            return bestIndex
        }
        return -1
    }

    private fun evaluateDahai(
            tehais: List<Hai>, furos: List<Mentsu>, minShantensu: Int): List<DahaiEvaluationResult?> {
        val algorithm = DahaiEvaluationAlgorithm(tehais, minShantensu)
        runAlgorithm(tehais, furos, algorithm)
        return algorithm.evaluationResults
    }

    private class DahaiEvaluationAlgorithm(private val tehais: List<Hai>, private val minShantensu: Int) : TehaiAnalysisAlgorithm {
        private val evaluationVector: Array<DahaiEvaluationResult?>

        init {
            this.evaluationVector = arrayOfNulls<DahaiEvaluationResult>(NUM_HAI_ID)
            for (haiId in 0..NUM_HAI_ID - 1) {
                this.evaluationVector[haiId] = DahaiEvaluationResult()
            }
        }

        override val maxShantensu: Int = minShantensu

        val evaluationResults: List<DahaiEvaluationResult?>
            get() {
                val evaluationResults = ArrayList<DahaiEvaluationResult?>()
                for (haiIndex in tehais.indices) {
                    evaluationResults.add(evaluationVector[tehais[haiIndex].idFZ])
                }
                return evaluationResults
            }

        override fun processChitoitsu(tehais: List<Hai>, toitsuIds: List<Int>,
                                      currentVector: IntArray, targetVector: IntArray) {
            for (dahaiId in 0..NUM_HAI_ID - 1) {
                if (currentVector[dahaiId] == 0) {
                    continue
                }
                if (currentVector[dahaiId] == 2) {
                    continue
                }
                if (evaluationVector[dahaiId] == null)
                    continue
                evaluationVector[dahaiId]!!.isMinShantensu = true
                for (candidateHaiId in 0..NUM_HAI_ID - 1) {
                    if (dahaiId != candidateHaiId && currentVector[candidateHaiId] == 1) {
                        evaluationVector[dahaiId]!!.nextCandidates[candidateHaiId] = true
                    }
                }
            }
        }

        override fun processNormalHora(tehais: List<Hai>, furos: List<Mentsu>,
                                       mentsuIds: List<Int>, jantoId: Int,
                                       currentVector: IntArray, targetVector: IntArray) {
            for (dahaiHaiId in 0..NUM_HAI_ID - 1) {
                if (currentVector[dahaiHaiId] == 0) {
                    continue
                }
                if (currentVector[dahaiHaiId] <= targetVector[dahaiHaiId]) {
                    continue
                }
                if (evaluationVector[dahaiHaiId] == null)
                    continue
                evaluationVector[dahaiHaiId]!!.isMinShantensu = true
                for (candidateHaiId in 0..NUM_HAI_ID - 1) {
                    if (candidateHaiId == dahaiHaiId) {
                        continue
                    }
                    if (currentVector[candidateHaiId] < targetVector[candidateHaiId]) {
                        evaluationVector[dahaiHaiId]!!.nextCandidates[candidateHaiId] = true
                    }
                }
            }
        }
    }

    private class DahaiEvaluationResult constructor() : Comparable<DahaiEvaluationResult> {
        var isMinShantensu: Boolean = false
        val nextCandidates: BooleanArray

        init {
            nextCandidates = BooleanArray(NUM_HAI_ID)
        }

        override fun compareTo(other: DahaiEvaluationResult): Int {
            if (!isMinShantensu && !other.isMinShantensu) return 0
            if (!isMinShantensu && other.isMinShantensu) return -1
            if (isMinShantensu && !other.isMinShantensu) return 1

            return compare(countTrue(nextCandidates), countTrue(other.nextCandidates))
        }

        private fun countTrue(values: BooleanArray): Int {
            var count = 0
            for (value in values) {
                if (value) {
                    count++
                }
            }
            return count
        }

        private fun compare(lhs: Int, rhs: Int): Int {
            return if (lhs < rhs) -1 else if (lhs == rhs) 0 else 1
        }
    }
}
