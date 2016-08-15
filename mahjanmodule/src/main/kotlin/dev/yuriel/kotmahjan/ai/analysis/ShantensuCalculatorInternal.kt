/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 yuriel
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *
 */

package dev.yuriel.kotmahjan.ai.analysis

/**
 * Created by yuriel on 7/25/16.
 * <a href="http://ara.moo.jp/mjhmr/shanten.htm" />
 */

class ShantensuCalculatorInternal constructor(private val currentVector: IntArray, private val numFuros: Int) {
    private val MAX_SHANTENSU = 8
    private var minShantensu: Int = 0

    init {
        this.minShantensu = MAX_SHANTENSU
    }

    fun calculateShantensu(): Int {
        tryChitoitsu()
        tryNormalHora()
        return minShantensu
    }

    private fun tryChitoitsu() {
        if (numFuros > 0) {
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
            chitoitsuShantensu = numSingles + (numRequiredPairs - numSingles) * 2 - 1
        }
        minShantensu = Math.min(chitoitsuShantensu, minShantensu)
    }

    private fun tryNormalHora() {
        tryNormalHoraSearchMentsus(4 - numFuros, 0, IntArray(NUM_HAI_ID))
    }

    private fun tryNormalHoraSearchMentsus(
            numRemainingMentsus: Int, minMentsuId: Int, targetVector: IntArray) {
        if (numRemainingMentsus == 0) {
            tryNormalHoraSearchJanto(targetVector)
            return
        }

        for (mentsuId in minMentsuId..NUM_MENTSU_ID - 1) {
            MentsuUtil.addMentsu(targetVector, mentsuId)
            val shantensuLowerBound = getDistance(currentVector, targetVector) - 1
            if (isValidTargetVector(targetVector) && shantensuLowerBound < minShantensu) {
                tryNormalHoraSearchMentsus(numRemainingMentsus - 1, mentsuId, targetVector)
            }
            MentsuUtil.removeMentsu(targetVector, mentsuId)
        }
    }

    private fun tryNormalHoraSearchJanto(targetVector: IntArray) {
        for (haiId in 0..NUM_HAI_ID - 1) {
            targetVector[haiId] += 2
            if (isValidTargetVector(targetVector)) {
                val shantensu = getDistance(currentVector, targetVector) - 1
                minShantensu = Math.min(shantensu, minShantensu)
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