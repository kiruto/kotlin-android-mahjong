package dev.yuriel.kotmahjan.ctrl.analysis

import android.util.SparseArray
import dev.yuriel.kotmahjan.models.IllegalIntArrayException
import dev.yuriel.kotmahjan.models.NoSuchTileException
import java.util.*

/**
 * Created by yuriel on 7/28/16.
 * 注意：
 *  牌のIDは配列のインデックスを等しくないこと。
 *  IntArrayから得たIntはインデックスを指し、それほかのIntはID
 */

/**
 * 牌の効率
 */
@Throws(IllegalIntArrayException::class, NoSuchTileException::class)
fun efficiency(tehai: IntArray, id: Int): Efficiency2Key {
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
    return Efficiency2Key(result, keys)
}

/**
 * 牌の距離
 *  -1: 無関係
 *  0: 同じ牌
 *  1: 隣の牌
 *  2: 離れる
 */
@Throws(NoSuchTileException::class)
fun distance(fromId: Int, toId: Int): Distance2Key {
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
fun excludeShuntsu(tehai: IntArray, antiOriented: Boolean = false): IntArray {
    if (tehai.size != 34) {
        throw IllegalIntArrayException(tehai.size)
    }
    val result = tehai.clone()
    val range = if (antiOriented) (tehai.size - 3 - 7) downTo 0 else 0..tehai.size - 3 - 7
    loop@
    for (i in range) {
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

@Throws(IllegalIntArrayException::class)
fun excludeBy2Group(tehai: IntArray): IntArray {
    if (tehai.size != 34) {
        throw IllegalIntArrayException(tehai.size)
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

@Throws(IllegalIntArrayException::class)
fun exclude2Correlation(tehai: IntArray): Useless2Key2KeyMap {
    if (tehai.size != 34) {
        throw IllegalIntArrayException(tehai.size)
    }
    //val map = SparseArray<MutableList<Int>>()
    val map = HashMap<Int, MutableList<Int>>()
    /*
    fun SparseArray<MutableList<Int>>.addTo(id: Int, addId: Int) {
        if (this[id] == null) {
            put(id, mutableListOf(addId))
        } else {
            this[id]!!.add(addId)
        }
    }
    */

    fun HashMap<Int, MutableList<Int>>.addTo(id: Int, addId: Int) {
        if (this[id] == null) {
            put(id, mutableListOf(addId))
        } else {
            this[id]!!.add(addId)
        }
    }

    fun IntArray.addKey(k1: Int, k2: Int, vararg fromId: Int) {
        if (0 < k1) {
            this[k1 - 1] += 1
            for (i in fromId) {
                map.addTo(k1, i)
            }
        }
        if (0 < k2) {
            this[k2 - 1] += 1
            for (i in fromId) {
                map.addTo(k2, i)
            }
        }
    }

    val useless = tehai.clone()
    val keyList = IntArray(34)
    for (i in 0..tehai.size - 1) {
        while (useless[i] > 0) {
            //print(i)
            var found = false
            var key: Distance2Key
            if (i < useless.size - 2 && useless[i + 1] > 0) {
                key = distance(i + 1, i + 2)
                if (key.distance != -1) {
                    useless[i] -= 1
                    useless[i + 1] -= 1
                    found = true
                    keyList.addKey(key.key1, key.key2, i + 1, i + 2)
                }
            }
            if (found) continue
            if (i < useless.size - 2 && useless[i + 2] > 0) {
                key = distance(i + 1, i + 3)
                if (key.distance != -1) {
                    useless[i] -= 1
                    useless[i + 2] -= 1
                    found = true
                    keyList.addKey(key.key1, key.key2, i + 1, i + 3)
                }
            }
            if (found) continue
            if (useless[i] > 1) {
                useless[i] -= 2
                key = Distance2Key(0, i + 1, -1)
                found = true
                keyList.addKey(key.key1, key.key2, i + 1)
            }
            if (!found) break
        }
    }
    return Useless2Key2KeyMap(useless, keyList, map)
}

@Throws(IllegalIntArrayException::class)
fun getUseless(tehai: IntArray): Useless2Key2KeyMap {
    if (tehai.size != 34) {
        throw IllegalIntArrayException(tehai.size)
    }
    val list1 = excludeBy2Group(excludeShuntsu(tehai))
    val list2 = excludeBy2Group(excludeShuntsu(tehai, true))
    val merge = IntArray(34) { i -> 0 }
    for (i in 0..list1.size - 1) {
        val value = list1[i]
        if (list2[i] == value) merge[i] = value
    }
    return exclude2Correlation(merge)
}

@Throws(IllegalIntArrayException::class)
fun breakTileGroup(data: Useless2Key2KeyMap) {
    if (data.useless.size != 34) {
        throw IllegalIntArrayException(data.useless.size)
    }
    if (data.keys.size != 34) {
        throw IllegalIntArrayException(data.keys.size)
    }

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

/**
 * @param distance 2つの牌の距離
 * @param key1 id
 * @param key2 id
 */
data class Distance2Key(val distance: Int = -1, val key1: Int = -1, val key2: Int = -1)

/**
 * @param efficiency 効率、高いほど効率が悪い
 * @param keys 欲しいの牌のID
 */
data class Efficiency2Key(val efficiency: Int = 0, val keys: Set<Int> = mutableSetOf())

/**
 * @param useless 捨てるはずの牌
 * @param keys また手に入れなくて欲しい牌
 * @param map キー牌ID：キー牌を判断する根拠
 */
data class Useless2Key2KeyMap(val useless: IntArray = IntArray(34),
                              val keys: IntArray = IntArray(34),
                              val map: HashMap<Int, MutableList<Int>> = HashMap()
                              /*val map: SparseArray<MutableList<Int>> = SparseArray()*/) {
    override fun hashCode(): Int {
        return (0.3 * useless.hashCode() + 0.3 * keys.hashCode() + 0.4 * map.hashCode()).hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}