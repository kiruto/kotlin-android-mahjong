package dev.yuriel.kotmahjan.ctrl.analysis

import dev.yuriel.kotmahjan.models.MahjanException
import dev.yuriel.kotmahjan.models.NoSuchTileException

/**
 * Created by yuriel on 7/28/16.
 * 注意：
 *  牌のIDは配列のインデックスを等しくないこと。
 */

/**
 * 牌の効率
 */
@Throws(MahjanException::class, NoSuchTileException::class)
fun efficiency(tehai: IntArray, id: Int): EfficiencyKey {
    if (tehai.size != 34) {
        throw MahjanException("手牌の形が違います。(size = ${tehai.size})")
    }
    if (id < 1 || id > 34) {
        throw NoSuchTileException(id)
    }
    var result = 0
    val keys = mutableSetOf<Int>()
    for (i in 0..tehai.size - 1) {
        if (tehai[i] == 0) continue
        if (id == i + 1) {
            keys.addAll(getEffectWith(id))
        }
        val (d, k1, k2) = distance(id, i + 1)
        if (-1 == d) {
            result += 10
        } else {
            result += d
            if (k1 != -1) keys.add(k1)
            if (k2 != -1) keys.add(k2)
        }
    }
    return EfficiencyKey(result, keys)
}

/**
 * 牌の距離
 *  -1: 無関係
 *  0: 同じ牌
 *  1: 隣の牌
 *  2: 離れる
 */
@Throws(NoSuchTileException::class)
fun distance(fromId: Int, toId: Int): DistanceKey {
    if (fromId < 1 || fromId > 34) {
        throw NoSuchTileException(fromId)
    }
    if (toId < 1 || toId > 34) {
        throw NoSuchTileException(toId)
    }
    if (fromId == toId) return DistanceKey(0, fromId)
    if (Math.abs(fromId - toId) > 2) return DistanceKey()
    if (fromId > 27 || toId > 27) return DistanceKey()
    when (fromId + toId) {
        18, 19, 20, 36, 37, 38 -> return DistanceKey()
    }
    val distance = Math.abs(fromId - toId)
    var k1: Int = -1
    var k2: Int = -1
    when (distance) {
        1 -> {
            k1 = when (fromId + toId) {
                3, 21, 39 -> -1
                else -> (fromId + toId - 1) / 2 - 1
            }
            k2 = when (fromId + toId) {
                17, 35, 53 -> -1
                else -> (fromId + toId + 1) /2 + 1
            }
        }
        2 -> {
            k1 = (fromId + toId) / 2
            k2 = -1
        }
    }
    return DistanceKey(distance, k1, k2)
}

@Throws(MahjanException::class)
fun excludeShuntsu(tehai: IntArray): IntArray {
    if (tehai.size != 34) {
        throw MahjanException("手牌の形が違います。(size = ${tehai.size})")
    }
    val result = tehai.clone()
    loop@
    for (i in 0..tehai.size - 3 - 7) {
        when (i + 1) {
            8, 9, 17, 18, 26, 27 -> continue@loop
            else -> {
                while (result[i] != 0) {
                    if (result[i] > 0 && result[i + 1] > 0 && result[i + 2] > 0) {
                        result[i] -= 1
                        result[i + 1] -= 1
                        result[i + 2] -= 1
                    } else {
                        continue@loop
                    }
                }
            }
        }
    }
    return result
}

@Throws(MahjanException::class)
fun excludedoitsu(tehai: IntArray): IntArray {
    if (tehai.size != 34) {
        throw MahjanException("手牌の形が違います。(size = ${tehai.size})")
    }
    val result = tehai.clone()
    loop@
    for (i in 0..tehai.size - 1) {
        if (result[i] > 2) {
            result[i] -= 3
        } else {
            continue@loop
        }
    }
    return result
}

@Throws(MahjanException::class)
fun exclude2Correlation(tehai: IntArray): Pair<IntArray, IntArray> {
    if (tehai.size != 34) {
        throw MahjanException("手牌の形が違います。(size = ${tehai.size})")
    }
    fun IntArray.addKey(k1: Int, k2: Int) {
        if (0 < k1) {
            this[k1 - 1] += 1
        }
        if (0 < k2) {
            this[k2 - 1] += 1
        }
    }

    val result = tehai.clone()
    val keyList = IntArray(34)
    for (i in 0..tehai.size - 1) {
        while (result[i] > 0) {
            //print(i)
            var found = false
            var key: DistanceKey
            if (i < result.size - 2 && result[i + 1] > 0) {
                key = distance(i + 1, i + 2)
                if (key.distance != -1) {
                    result[i] -= 1
                    result[i + 1] -= 1
                    found = true
                    keyList.addKey(key.key1, key.key2)
                }
            }
            if (found) continue
            if (i < result.size - 2 && result[i + 2] > 0) {
                key = distance(i + 1, i + 3)
                if (key.distance != -1) {
                    result[i] -= 1
                    result[i + 2] -= 1
                    found = true
                    keyList.addKey(key.key1, key.key2)
                }
            }
            if (found) continue
            if (result[i] > 1) {
                result[i] -= 2
                key = DistanceKey(0, i + 1, -1)
                found = true
                keyList.addKey(key.key1, key.key2)
            }
            if (!found) break
        }
    }
    return Pair(result, keyList)
}

@Throws(MahjanException::class)
fun getUseless(tehai: IntArray): Pair<IntArray, IntArray> {
    if (tehai.size != 34) {
        throw MahjanException("手牌の形が違います。(size = ${tehai.size})")
    }
    return exclude2Correlation(excludedoitsu(excludeShuntsu(tehai)))
}

fun getEffectWith(id: Int): List<Int> {
    val result = mutableListOf<Int>()
    if (id > 27) {
        result.add(id)
    } else {
        when (id) {
            1, 10, 19 -> {
                result.add(id)
                result.add(id + 1)
            }
            9, 18, 27 -> {
                result.add(id - 1)
                result.add(id)
            }
            else -> {
                result.add(id - 1)
                result.add(id)
                result.add(id + 1)
            }
        }
    }
    return result
}

@Throws(RuntimeException::class)
private operator fun IntArray.minus(other: IntArray): IntArray {
    if (size != other.size) {
        throw RuntimeException()
    }
    val result = IntArray(size)
    for (i in 0..size - 1) {
        result[i] = this[i] - other[i]
    }
    return result
}

@Throws(RuntimeException::class)
private operator fun IntArray.plus(other: IntArray): IntArray {
    if (size != other.size) {
        throw RuntimeException()
    }
    val result = IntArray(size)
    for (i in 0..size - 1) {
        result[i] = this[i] + other[i]
    }
    return result
}

data class DistanceKey(val distance: Int = -1, val key1: Int = -1, val key2: Int = -1)

/**
 * @param efficiency 効率、高いほど効率が悪い
 * @param keys 欲しいの牌のID
 */
data class EfficiencyKey(val efficiency: Int = 0, val keys: Set<Int> = mutableSetOf())