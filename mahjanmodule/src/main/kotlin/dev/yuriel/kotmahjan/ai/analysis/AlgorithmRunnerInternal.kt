/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.ai.analysis

import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.collections.Mentsu
import java.util.*

/**
 * Created by yuriel on 7/26/16.
 */
class AlgorithmRunnerInternal(
        private val tehais: List<Hai>, private val furos: List<Mentsu>, private val algorithm: TehaiAnalysisAlgorithm) {
    private val currentVector: IntArray
    private val maxShantensu: Int

    init {
        this.currentVector = haiListToCountVector(tehais)
        this.maxShantensu = algorithm.maxShantensu
    }

    fun runAlgorithm() {
        tryChitoitsu()
        tryNormalHora()
    }

    private fun tryChitoitsu() {
        if (furos.size > 0) {
            return
        }

        var numPairs = 0
        var numSingles = 0
        for (haiId in 0..NUM_HAI_ID - 1) {
            if (currentVector[haiId] >= 2) {
                numPairs++
            } else if (currentVector[haiId] == 1) {
                numSingles++
            }
        }

        val numRequiredPairs = 7 - numPairs
        val chitoitsuShantensu: Int
        if (numSingles >= numRequiredPairs) {
            chitoitsuShantensu = numRequiredPairs - 1
        } else {
            // This case is rarely important.
            chitoitsuShantensu = numSingles + (numRequiredPairs - numSingles) * 2 - 1
        }

        if (chitoitsuShantensu > maxShantensu) {
            return
        }

        val toitsuIds = ArrayList<Int>()
        val targetVector = IntArray(NUM_HAI_ID)
        for (haiId in 0..NUM_HAI_ID - 1) {
            if (currentVector[haiId] >= 2) {
                toitsuIds.add(haiId)
                targetVector[haiId] = 2
            }
        }
        tryChitoitusSearchToitsus(numRequiredPairs, 0, toitsuIds, targetVector)
    }

    private fun tryChitoitusSearchToitsus(
            numRemainingToitsus: Int, minToitsuId: Int,
            toitsuIds: MutableList<Int>, targetVector: IntArray) {
        if (numRemainingToitsus == 0) {
            algorithm.processChitoitsu(tehais, toitsuIds, currentVector, targetVector)
            return
        }

        for (toitsuId in 0..NUM_HAI_ID - 1) {
            if (currentVector[toitsuId] >= 2) {
                continue
            } else if (currentVector[toitsuId] == 1) {
                toitsuIds.add(toitsuId)
                targetVector[toitsuId] = 2
                tryChitoitusSearchToitsus(
                        numRemainingToitsus - 1, toitsuId + 1, toitsuIds, targetVector)
                toitsuIds.removeAt(toitsuIds.size - 1)
                targetVector[toitsuId] = 0
            }
            // We ignore cases when we construct a toitsu of hais which a player doesn't have
            // any.
        }
    }

    private fun tryNormalHora() {
        tryNormalHoraSearchMentsus(
                4 - furos.size, 0, ArrayList<Int>(), IntArray(NUM_HAI_ID))
    }

    private fun tryNormalHoraSearchMentsus(
            numRemainingMentsus: Int, minMentsuId: Int,
            tehaiMentsuIds: MutableList<Int>, targetVector: IntArray) {
        if (numRemainingMentsus == 0) {
            tryNormalHoraSearchJanto(tehaiMentsuIds, targetVector)
            return
        }

        for (mentsuId in minMentsuId..NUM_MENTSU_ID - 1) {
            MentsuUtil.addMentsu(targetVector, mentsuId)
            tehaiMentsuIds.add(mentsuId)
            val shantensuLowerBound = getDistance(currentVector, targetVector) - 1
            if (isValidTargetVector(targetVector) && shantensuLowerBound <= maxShantensu) {
                tryNormalHoraSearchMentsus(
                        numRemainingMentsus - 1, mentsuId, tehaiMentsuIds, targetVector)
            }
            MentsuUtil.removeMentsu(targetVector, mentsuId)
            tehaiMentsuIds.removeAt(tehaiMentsuIds.size - 1)
        }
    }

    private fun tryNormalHoraSearchJanto(mentsuIds: List<Int>, targetVector: IntArray) {
        for (haiId in 0..NUM_HAI_ID - 1) {
            targetVector[haiId] += 2
            if (isValidTargetVector(targetVector)) {
                val shantensu = getDistance(currentVector, targetVector) - 1
                if (shantensu <= maxShantensu) {
                    algorithm.processNormalHora(
                            tehais, furos, mentsuIds, haiId, currentVector, targetVector)
                }
            }
            targetVector[haiId] -= 2
        }
        return
    }

    private fun isValidTargetVector(targetVector: IntArray): Boolean {
        for (haiId in 0..NUM_HAI_ID - 1) {
            if (targetVector[haiId] > 4) {
                return false
            }
        }
        return true
    }
}