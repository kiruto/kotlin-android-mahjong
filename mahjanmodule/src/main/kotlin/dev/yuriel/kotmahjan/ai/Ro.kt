///*
// * Copyright (C) 2016. Yuriel - All Rights Reserved
// * Unauthorized copying of this file, via any medium is strictly prohibited
// * Proprietary and confidential
// */
//
//package dev.yuriel.kotmahjan.ai
//
//import dev.yuriel.kotmahjan.ai.AI
//import dev.yuriel.kotmahjan.ai.analysis.*
//import dev.yuriel.kotmahjan.models.*
//import dev.yuriel.kotmahjan.models.collections.Tehai
//import dev.yuriel.kotmahjan.models.toTypedHaiArray
//
///**
// * Created by yuriel on 7/30/16.
// * 第二号ロボット：呂
// * 伊野ライバルとして存在しています
// */
//abstract class Ro: AI() {
//    private var tehai: Tehai = Tehai()
//
//    override fun receive(hai: Hai) {
//        tehai.put(hai)
//        tehai.sort()
//    }
//
//    override fun da(haiList: List<Hai>, basis: List<Hai>): Hai {
//        var resultHai: Hai
//        var u = getUselessGeneralized(tehai.toTypedArray(false))
//        var result = printResultByGen(u, tehai, false)
//        if (result.first != 0) {
//            resultHai = Hai.newInstance(result.first)
//            outln("da: $resultHai")
//        } else {
//            u = getUselessSpecialized(tehai.toTypedArray(false))
//            result = printResultByGen(u, tehai, true)
//        }
//
//        if (result.first != 0) {
//            resultHai = Hai.newInstance(result.first)
//            outln("da: $resultHai")
//        } else {
//            val array = toTypedHaiArray(basis)
//            val b = sortEffectInRange(u, tehai.toTypedArray(), array)
//            resultHai = Hai.newInstance(b.g2kList[0].group[0])
//            outln("extreme da: $resultHai")
//
//        }
//
//        for ((e, id) in result.second) {
//            outln("hai: ${Hai.newInstance(id)}, efficiencyByHand: ${e.efficiency}")
//            for (h in e.keys) {
//                outln("   >${Hai.newInstance(h)}, ")
//            }
//        }
//        println()
//        return resultHai
//    }
//
//    override fun remove(hai: Hai) {
//        tehai.remove(hai)
//    }
//
//    override fun store(haiList: List<Hai>) {
//        tehai.put(haiList)
//    }
//
//    override fun clear() {
//        tehai.clear()
//    }
//
//    override fun getHai(): List<Hai> = tehai.haiList
//
//    override fun getHaiRaw(): String = tehai.toString()
//
//    private fun printResultByGen(u: Useless2Key2KeyMap, tehai: Tehai, hl: Boolean = false):
//            Pair<Int, List<Pair<Efficiency2Key, Int>>> {
//        out("${if (hl) ANSI_YELLOW else ANSI_CYAN}")
//        out("sute: ")
//        for (i in 0..u.useless.size - 1) {
//            //out("${u.first[i]}, ")
//            if (0 == u.useless[i]) continue
//            out("${Hai.newInstance(i + 1)},")
//        }
//        println()
//        outln("want: ")
//        for (i in 0..u.keys.size - 1) {
//            if (0 == u.keys[i]) continue
//            out("   >${Hai.newInstance(i + 1)}, ")
//            out("Because: ")
//            for (b in u.k2gMap[i + 1]!!) {
//                out("${Hai.newInstance(b)},")
//            }
//            println()
//        }
//        println()
//
//        var resultId = 0
//        var efficiency = Efficiency2Key()
//        val list = mutableListOf<Pair<Efficiency2Key, Int>>()
//        for (i in 0..u.useless.size - 1) {
//            if (u.useless[i] < 1) continue
//            val e = efficiencyByHand(tehai.toTypedArray(false), i + 1)
//            list.add(Pair(e, i + 1))
//            if (e.efficiency > efficiency.efficiency) {
//                efficiency = e
//                resultId = i + 1
//            }
//        }
//        out(ANSI_RESET)
//        return Pair(resultId, list)
//    }
//
//    private fun out(str: String) {
//        msg = str
//    }
//
//    private fun outln(str: String) {
//        msg = str + "\n"
//    }
//
//    @Throws(IllegalIntArrayException::class)
//    private fun getUselessGeneralized(tehai: IntArray): Useless2Key2KeyMap {
//        return getUselessByMerged(tehai) { list1, list2 ->
//            IntArray(34) { i ->
//                val value = list1[i]
//                if (value == list2[i]) value else 0
//            }
//        }
//    }
//
//    /*
//    @Throws(IllegalIntArrayException::class)
//    fun getUselessSpecialized(tehai: IntArray): Useless2Key2KeyMap {
//        getUselessByMerged(tehai) { list1, list2 ->
//
//        }
//    }
//    */
//
//    private fun getUselessByMerged(tehai: IntArray,
//                                   mergeFunc: (IntArray, IntArray) -> IntArray): Useless2Key2KeyMap{
//        if (tehai.size != 34) {
//            throw IllegalIntArrayException(tehai.size)
//        }
//        var mentus = 0
//        var janto = false
//        fun findShuntsu(i1: Int, i2: Int, i3: Int, i: IntArray): Boolean {
//            mentus ++
//            return true
//        }
//
//        fun findKotsu(i1: Int, i: IntArray): Boolean {
//            mentus ++
//            return true
//        }
//        val list1 = excludeKotsu(excludeShuntsu(tehai, false, ::findShuntsu), ::findKotsu)
//        val list2 = excludeKotsu(excludeShuntsu(tehai, true))
//        val merge = mergeFunc(list1, list2)
//        return exclude2Correlation(merge)
//    }
//}