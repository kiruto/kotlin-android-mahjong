package dev.yuriel.kotmahjan.ctrl.analysis

import dev.yuriel.kotmahjan.models.Hai

/**
 * Created by yuriel on 7/25/16.
 */
object HaiUtil {

    fun isYaochuhai(id: Int): Boolean {
        val haiId = id - 1
        return haiId == 0 || haiId == 8 ||
                haiId == 9 || haiId == 17 ||
                haiId == 18 || haiId == 26 ||
                27 <= haiId && haiId < 34
    }

    fun isSangenpai(id: Int): Boolean {
        return 32 <= id && id < 35
    }

    fun haiListToCountVector(haiList: List<Hai>): IntArray {
        val countVector = IntArray(NUM_HAI_ID)
        for (hai in haiList) {
            countVector[hai.id - 1]++
        }
        return countVector
    }
}
