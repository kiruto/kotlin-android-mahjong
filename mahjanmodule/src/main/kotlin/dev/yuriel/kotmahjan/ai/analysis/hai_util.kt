/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 yuriel<yuriel3183@gmail.com>
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
 * SOFTWARE.
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