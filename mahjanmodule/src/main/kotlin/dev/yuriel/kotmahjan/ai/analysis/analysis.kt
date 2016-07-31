/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.ai.analysis

import android.util.SparseArray
import dev.yuriel.kotmahjan.models.IllegalIntArrayException
import dev.yuriel.kotmahjan.models.NoSuchTileException
import java.util.*
import kotlin.annotation.AnnotationTarget.*

/**
 * Created by yuriel on 7/28/16.
 * 注意：
 *  牌のIDは配列のインデックスを等しくないこと。
 *  IntArrayから得たIntはインデックスを指し、それほかのIntはID
 *  IntArrayはツールだけとして使用している、それ以外の牌(例えば赤ドラ牌)の表示はできません。
 */

/**
 * range(1, 34)
 */
@Target(TYPE, TYPE_PARAMETER, LOCAL_VARIABLE, PROPERTY, FIELD, FUNCTION, EXPRESSION, VALUE_PARAMETER)
@Retention(AnnotationRetention.SOURCE)
annotation class ID

/**
 * range(1, 33)
 */
@Target(TYPE, TYPE_PARAMETER, LOCAL_VARIABLE, PROPERTY, FIELD, FUNCTION, EXPRESSION, VALUE_PARAMETER)
@Retention(AnnotationRetention.SOURCE)
annotation class Index

/**
 * @param distance 2つの牌の距離
 * @param key1 id
 * @param key2 id
 */
data class Distance2Key(val distance: Int = -1, val key1: @ID Int = -1, val key2: @ID Int = -1)

/**
 * @param efficiency 効率、高いほど効率が悪い
 * @param keys 欲しいの牌のID
 */
data class Efficiency2Key(val efficiency: Int = 0, val keys: Set<@ID Int> = mutableSetOf())

data class Group2KeyList(val group: List<@ID Int>,
                         val keys: List<@ID Int>,
                         var probability: Float? = null): Comparable<Group2KeyList> {
    override fun compareTo(other: Group2KeyList): Int {
        return (((probability?:0F) - (other.probability?:0F)) * 10000).toInt()
    }
}

/**
 * @param useless 捨てるはずの牌
 * @param keys また手に入れなくて欲しい牌
 * @param k2gMap キー牌ID：キー牌を判断する根拠
 */
data class Useless2Key2KeyMap(val useless: IntArray = IntArray(34),
                              val keys: IntArray = IntArray(34),
                              val k2gMap: HashMap<@ID Int, MutableList<@ID Int>> = HashMap(),
                              /*val map: SparseArray<MutableList<Int>> = SparseArray(),*/
                              val g2kList: List<Group2KeyList> = listOf<Group2KeyList>()) {
    override fun hashCode(): Int {
        return (0.3 * useless.hashCode() +
                0.3 * keys.hashCode() +
                0.2 * k2gMap.hashCode() +
                0.2 * g2kList.hashCode())
                .hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}


/**
 * 牌の効率
 */
@Throws(IllegalIntArrayException::class, NoSuchTileException::class)
fun efficiencyByHand(tehai: IntArray, id: @ID Int): Efficiency2Key {
    if (tehai.size != 34) {
        throw IllegalIntArrayException(tehai.size)
    }
    if (id < 1 || id > 34) {
        throw NoSuchTileException(id)
    }
    var result = 0
    val keys = mutableSetOf<Int>()
    for (i in 0..tehai.size - 1) {
        if (tehai[i] == 0) continue
        if (id == i + 1) {
            keys.addAll(getTileGroupRangeWith(id))
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
    return Efficiency2Key(result, keys)
}

@Throws(IllegalIntArrayException::class, NoSuchTileException::class)
fun efficiencyByProbability(range: IntArray, id: @ID Int): Float {
    if (range.size != 34) {
        throw IllegalIntArrayException(range.size)
    }
    if (id < 1 || id > 34) {
        throw NoSuchTileException(id)
    }
    val keys = mutableSetOf<Int>()
    var result = 0F
    keys.addAll(getTileGroupRangeWith(id))
    for (k in keys) {
        result += range[k - 1].toFloat() / range.sum().toFloat()
    }
    return result
}

/**
 * 牌の距離
 *  -1: 無関係
 *  0: 同じ牌
 *  1: 隣の牌
 *  2: 離れる
 */
@Throws(NoSuchTileException::class)
fun distance(fromId: @ID Int, toId: @ID Int): Distance2Key {
    if (fromId < 1 || fromId > 34) {
        throw NoSuchTileException(fromId)
    }
    if (toId < 1 || toId > 34) {
        throw NoSuchTileException(toId)
    }
    if (fromId == toId) return Distance2Key(0, fromId)
    if (Math.abs(fromId - toId) > 2) return Distance2Key()
    if (fromId > 27 || toId > 27) return Distance2Key()
    when (fromId + toId) {
        18, 19, 20, 36, 37, 38 -> return Distance2Key()
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
    return Distance2Key(distance, k1, k2)
}

@Throws(IllegalIntArrayException::class)
fun excludeShuntsu(tehai: IntArray,
                   antiOriented: Boolean = false,
                   confirm: ((@ID Int, @ID Int, @ID Int, last: @Index IntArray) -> Boolean)? = null): IntArray {
    if (tehai.size != 34) {
        throw IllegalIntArrayException(tehai.size)
    }
    val result = tehai.clone()
    val range = if (antiOriented) (tehai.size - 3 - 7) downTo 0 else 0..tehai.size - 3 - 7
    loop@
    for (@Index i in range) {
        when (i + 1) {
            8, 9, 17, 18, 26, 27 -> continue@loop
            else -> {
                while (result[i] != 0) {
                    if (result[i] > 0 && result[i + 1] > 0 && result[i + 2] > 0) {
                        if (confirm?.invoke(i + 1, i + 2, i + 3, result)?:true) {
                            result[i] -= 1
                            result[i + 1] -= 1
                            result[i + 2] -= 1
                        }
                    } else {
                        continue@loop
                    }
                }
            }
        }
    }
    return result
}

@Throws(IllegalIntArrayException::class)
fun excludeKotsu(tehai: IntArray, confirm: ((@ID Int, @Index IntArray) -> Boolean)? = null): IntArray {
    if (tehai.size != 34) {
        throw IllegalIntArrayException(tehai.size)
    }
    val result = tehai.clone()
    loop@
    for (i in 0..tehai.size - 1) {
        if (result[i] > 2) {
            if (confirm?.invoke(i + 1, result)?:true) result[i] -= 3
        } else {
            continue@loop
        }
    }
    return result
}

@Throws(IllegalIntArrayException::class)
fun exclude2Correlation(tehai: IntArray,
                        confirm: ((@ID Int, @ID Int, useless: @Index IntArray) -> Boolean)? = null): Useless2Key2KeyMap {
    if (tehai.size != 34) {
        throw IllegalIntArrayException(tehai.size)
    }
    //val map = SparseArray<MutableList<Int>>()
    val k2gMap = HashMap<Int, MutableList<Int>>()
    val g2kList = mutableListOf<Group2KeyList>()
    var hasJanto = false;
    /*
    fun SparseArray<MutableList<Int>>.addTo(id: Int, addId: Int) {
        if (this[id] == null) {
            put(id, mutableListOf(addId))
        } else {
            this[id]!!.add(addId)
        }
    }
    */

    fun HashMap<Int, MutableList<Int>>.addTo(id: @ID Int, addId: @ID Int) {
        if (this[id] == null) {
            put(id, mutableListOf(addId))
        } else {
            this[id]!!.add(addId)
        }
    }

    fun IntArray.addKey(k1: @ID Int, k2: @ID Int, vararg fromId: @ID Int) {
        val keys = fromId.toList()
        val group = mutableListOf<Int>()
        if (0 < k1) {
            this[k1 - 1] += 1
            for (i in fromId) {
                k2gMap.addTo(k1, i)
                group.add(i)
            }
        }
        if (0 < k2) {
            this[k2 - 1] += 1
            for (i in fromId) {
                k2gMap.addTo(k2, i)
                group.add(i)
            }
        }
        g2kList.add(Group2KeyList(group, keys))
    }

    val useless = tehai.clone()
    val keyList = IntArray(34)
    for (i in 0..tehai.size - 1) {
        while (useless[i] > 0) {
            //print(i)
            var found = false
            var key: Distance2Key
            if (!hasJanto && useless[i] > 1 && confirm?.invoke(i + 1, i + 1, useless)?: true) {
                useless[i] -= 2
                key = Distance2Key(0, i + 1, -1)
                found = true
                keyList.addKey(key.key1, key.key2, i + 1)
                hasJanto = true
            }
            if (found) continue
            if (i < useless.size - 2 && useless[i + 1] > 0) {
                key = distance(i + 1, i + 2)
                if (key.distance != -1 && confirm?.invoke(i + 1, i + 2, useless)?: true) {
                    useless[i] -= 1
                    useless[i + 1] -= 1
                    found = true
                    keyList.addKey(key.key1, key.key2, i + 1, i + 2)
                }
            }
            if (found) continue
            if (i < useless.size - 2 && useless[i + 2] > 0) {
                key = distance(i + 1, i + 3)
                if (key.distance != -1 && confirm?.invoke(i + 1, i + 3, useless)?: true) {
                    useless[i] -= 1
                    useless[i + 2] -= 1
                    found = true
                    keyList.addKey(key.key1, key.key2, i + 1, i + 3)
                }
            }
            if (found) continue
            if (useless[i] > 1 && confirm?.invoke(i + 1, i + 1, useless)?: true) {
                useless[i] -= 2
                key = Distance2Key(0, i + 1, -1)
                found = true
                keyList.addKey(key.key1, key.key2, i + 1)
            }

            if (!found) break
        }
    }
    return Useless2Key2KeyMap(useless, keyList, k2gMap, g2kList)
}

@Throws(IllegalIntArrayException::class)
fun getUselessGeneralized(tehai: IntArray): Useless2Key2KeyMap {
    return getUselessByMerged(tehai) { list1, list2 ->
        IntArray(34) { i ->
            val value = list1[i]
            if (value == list2[i]) value else 0
        }
    }
}

// todo: 何かの違いがあるかも
@Deprecated("この方法は通用しないので、別の方法を考えてみて")
@Throws(IllegalIntArrayException::class)
fun getUselessSpecialized(tehai: IntArray): Useless2Key2KeyMap = getUselessByMerged(tehai) {
    list1, list2 -> list1 or list2
}

/**
 * @param mergeFunc フォワードで順子を除く結果とリバースの結果を併合する
 * @return 計算した結果
 */
@Throws(IllegalIntArrayException::class)
fun getUselessByMerged(tehai: IntArray,
                       mergeFunc: (IntArray, IntArray) -> IntArray): Useless2Key2KeyMap {
    if (tehai.size != 34) {
        throw IllegalIntArrayException(tehai.size)
    }
    val list1 = excludeKotsu(excludeShuntsu(tehai))
    val list2 = excludeKotsu(excludeShuntsu(tehai, true))
    val merge = mergeFunc(list1, list2)
    return exclude2Correlation(merge)
}

/**
 * どの牌の組みを分けるかを決めるメソード
 */
@Throws(IllegalIntArrayException::class)
fun sortEffectInRange(data: Useless2Key2KeyMap,
                      tehai: IntArray,
                      range: IntArray? = null): Useless2Key2KeyMap{
    if (data.useless.size != 34) {
        throw IllegalIntArrayException(data.useless.size)
    }
    if (data.keys.size != 34) {
        throw IllegalIntArrayException(data.keys.size)
    }

    val list = range?: IntArray(34) { i -> 4 - tehai[i] }
    val probabilityTable = HashMap<Int, Float>()
    for (i in 0..data.keys.size - 1) {
        if (data.keys[i] == 0) continue
        probabilityTable.put(i + 1, list[i].toFloat() / list.sum().toFloat())
    }
    for (id in data.g2kList) {
        id.probability = 0F
        for (j in id.keys) {
            id.probability = id.probability?:0F + (probabilityTable[j]?: 0F)
        }
    }
    Collections.sort(data.g2kList)
    return data
}

private fun getTileGroupRangeWith(id: @ID Int): List<Int> {
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
operator fun IntArray.minus(other: IntArray): IntArray {
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
operator fun IntArray.plus(other: IntArray): IntArray {
    if (size != other.size) {
        throw RuntimeException()
    }
    val result = IntArray(size)
    for (i in 0..size - 1) {
        result[i] = this[i] + other[i]
    }
    return result
}

@Throws(RuntimeException::class)
private infix fun IntArray.or(other: IntArray): IntArray {
    if (size != other.size) {
        throw RuntimeException()
    }
    val result = IntArray(size)
    for (i in 0..size - 1) {
        result[i] = if (this[i] != 0) this[i] else if (other[i] != 0) other[i] else 0
    }
    return result
}