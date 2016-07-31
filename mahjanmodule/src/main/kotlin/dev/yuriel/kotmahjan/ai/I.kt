/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.ai

import dev.yuriel.kotmahjan.ai.AI
import dev.yuriel.kotmahjan.ai.analysis.*
import dev.yuriel.kotmahjan.models.*
import dev.yuriel.kotmahjan.models.toTypedHaiArray
import rx.Observable
import java.util.*

/**
 * Created by yuriel on 7/30/16.
 * 第一号ロボット：伊
 * 鳴かないながらツモだけ麻雀プレイします
 */
class I: AI(), PlayerModel {
    override var tehai: Tehai = Tehai()
    override val kawa: Kawa = Kawa()
    override val mentsu: MutableList<Mentsu> = mutableListOf()
    override var tsumo: Hai? = null
    override var point: Int = 0

    override fun getObservable(event: RoundEvent, duration: Long): Observable<RoundEvent> {
        return Observable.create<RoundEvent> { t ->
            val e = RoundEvent()
            e.from = this@I
            when(event.action) {
                ACTION_SUTE -> {
                    val temp = ArrayList(tehai.haiList)
                    temp.add(event.hai)
                    if (calculateShantensu(temp, 0) == -1) {
                        e.action = ACTION_RON
                    }
                }
                else -> e.action = ACTION_NONE
            }
            t.onNext(e)
            t.onCompleted()
        }
    }

    override fun receive(hai: Hai) {
        tehai.put(hai)
        tehai.sort()
    }

    override fun da(haiList: List<Hai>, basis: List<Hai>): Hai {
        var resultHai: Hai
        var u = getUselessGeneralized(tehai.toTypedArray(false))
        val array = toTypedHaiArray(basis)
        var result = printResultByGen(u, tehai, array, false)
        if (result.first != 0) {
            resultHai = Hai.newInstance(result.first)
            outln("da: $resultHai")
        } else {
            u = getUselessSpecialized(tehai.toTypedArray(false))
            result = printResultByGen(u, tehai, array, true)
        }

        if (result.first != 0) {
            resultHai = Hai.newInstance(result.first)
            outln("da: $resultHai")
        } else {

            val b = sortEffectInRange(u, tehai.toTypedArray(), array)
            resultHai = Hai.newInstance(b.g2kList[0].group[0])
            outln("extreme da: $resultHai")

        }

        for ((e, id) in result.second) {
            outln("hai: ${Hai.newInstance(id)}, efficiencyByProbability: $e")
            /*
            for (h in e.keys) {
                outln("   >${Hai.newInstance(h)}, ")
            }
            */
        }
        println()
        return resultHai
    }

    override fun kan(haiList: List<Hai>): Boolean = false

    override fun pon(haiList: List<Hai>): Boolean = false

    override fun chi(haiList: List<Hai>): Boolean = false

    override fun ron(haiList: List<Hai>): Boolean = calculateShantensu(tehai.haiList, 0) < 0

    override fun remove(hai: Hai) {
        tehai.remove(hai)
    }

    override fun store(haiList: List<Hai>) {
        tehai.put(haiList)
    }

    override fun clear() {
        tehai.clear()
    }

    override fun getHai(): List<Hai> = tehai.haiList

    override fun getHaiRaw(): String = tehai.toString()

    private fun printResultByGen(u: Useless2Key2KeyMap,
                                 tehai: Tehai,
                                 basis: IntArray,
                                 hl: Boolean = false): Pair<Int, List<Pair<Float, Int>>> {
        out("${if (hl) ANSI_YELLOW else ANSI_CYAN}")
        out("sute: ")
        for (i in 0..u.useless.size - 1) {
            //out("${u.first[i]}, ")
            if (0 == u.useless[i]) continue
            out("${Hai.newInstance(i + 1)},")
        }
        println()
        outln("want: ")
        for (i in 0..u.keys.size - 1) {
            if (0 == u.keys[i]) continue
            out("   >${Hai.newInstance(i + 1)}, ")
            out("Because: ")
            for (b in u.k2gMap[i + 1]!!) {
                out("${Hai.newInstance(b)},")
            }
            println()
        }
        println()

        var resultId = 0
        //var efficiency = Efficiency2Key()
        var efficiency = 1F
        //val list = mutableListOf<Pair<Efficiency2Key, Int>>()
        val list = mutableListOf<Pair<Float, Int>>()
        for (i in 0..u.useless.size - 1) {
            if (u.useless[i] < 1) continue
            //val e = efficiencyByHand(tehai.toTypedHaiArray(false), i + 1)
            val e = efficiencyByProbability(basis, i + 1)
            list.add(Pair(e, i + 1))
            if (e < efficiency) {
                efficiency = e
                resultId = i + 1
            }
        }
        out(ANSI_RESET)
        return Pair(resultId, list)
    }

    private fun out(str: String) {
        msg = str
    }

    private fun outln(str: String) {
        msg = str + "\n"
    }

    @Throws(IllegalIntArrayException::class)
    private fun getUselessGeneralized(tehai: IntArray): Useless2Key2KeyMap {
        return getUselessByMerged(tehai) { list1, list2 ->
            IntArray(34) { i ->
                val value = list1[i]
                if (value == list2[i]) value else 0
            }
        }
    }

    /*
    @Throws(IllegalIntArrayException::class)
    fun getUselessSpecialized(tehai: IntArray): Useless2Key2KeyMap {
        getUselessByMerged(tehai) { list1, list2 ->

        }
    }
    */

    private fun getUselessByMerged(tehai: IntArray,
                                   mergeFunc: (IntArray, IntArray) -> IntArray): Useless2Key2KeyMap{
        if (tehai.size != 34) {
            throw IllegalIntArrayException(tehai.size)
        }
        var mentus = 0
        var janto = false
        fun findShuntsu(i1: Int, i2: Int, i3: Int, i: IntArray): Boolean {
            mentus ++
            return true
        }

        fun findKotsu(i1: Int, i: IntArray): Boolean {
            mentus ++
            return true
        }
        val list1 = excludeKotsu(excludeShuntsu(tehai, false, ::findShuntsu), ::findKotsu)
        val list2 = excludeKotsu(excludeShuntsu(tehai, true))
        val merge = mergeFunc(list1, list2)
        return exclude2Correlation(merge)
    }
}