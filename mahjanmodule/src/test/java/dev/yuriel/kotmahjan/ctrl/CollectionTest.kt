/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.ctrl

import dev.yuriel.kotmahjan.models.Tehai
import org.junit.Test

/**
 * Created by yuriel on 7/27/16.
 */
class CollectionTest {

    val mgr = HaiMgr()

    fun getTehai(): Tehai {
        val tehai = Tehai()

        while(!tehai.enough()){
            tehai.put(mgr.getHai())
        }
        return tehai
    }

    @Test
    fun testAllHai() {
        val all = HaiUtil().getAllHai()
//        for (hai in all) {
//            println(hai.toString())
//        }
        assert(all.size == 9 * 3 * 4 + 7 * 4) {
            "all hai size error: ${all.size} != ${9 * 3 * 4 + 7 * 4}"
        }
        val array = IntArray(27 + 7) { i -> 0 }
        for (hai in all) {
            array[hai.id - 1] += 1
        }
        for (index in array) {
            assert(array[index] == 4) { "index($index) size: ${array[index]}" }
        }
    }

    @Test
    fun testRandAllHai() {
        val all = HaiUtil().getAllHaiRand()
//        for (hai in all) {
//            println(hai.toString())
//        }
        assert(all.size == 9 * 3 * 4 + 7 * 4) {
            "all hai size error: ${all.size} != ${9 * 3 * 4 + 7 * 4}"
        }
        val array = IntArray(27 + 7) { i -> 0 }
        for (hai in all) {
            array[hai.id - 1] += 1
        }
        for (index in array) {
            assert(array[index] == 4) { "index($index) size: ${array[index]}" }
        }
    }

    @Test
    fun testHaiPai() {
        val tehai = Tehai()
        val mgr = HaiMgr()
        for (i in 0..2) {
            tehai.put(mgr.haiPai())
        }
        tehai.put(mgr.getHai())
        tehai.sort()
        println(tehai)
        assert(tehai.haiList.size == 13)
    }
}