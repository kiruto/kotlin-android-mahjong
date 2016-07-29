package dev.yuriel.kotmahjan.ctrl

import dev.yuriel.kotmahjan.ctrl.analysis.*
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.Tehai
import org.junit.Test

/**
 * Created by yuriel on 7/28/16.
 */
class analysisTest {

    @Test
    fun testShantensu() {
        val tehai = Tehai.fromString("m6,m8,p6,p7,p8,p8,p8,p9,s3,s8,s8,s9,N")
        println(calculateShantensu(tehai.haiList, listOf()))
    }

    @Test
    fun testDistance() {
        val mgr = HaiMgr()
        while (mgr.hasHai()) {
            val h1 = mgr.getHai()
            val h2 = mgr.getHai()
            val (d, k1, k2) = distance(h1.id, h2.id)
            val result = "distance = $d, h1 = $h1, h2 = $h2, k1 = ${if (k1 != -1) Hai.newInstance(k1) else k1}, k2 = ${if (k2 != -1) Hai.newInstance(k2) else -1}"
            println(result)
            assert(d > -2 && d < 3) { result }
            if (d == 0) assert(h1 sameAs h2)
        }
    }

    @Test
    fun testExcludeShuntsu() {
        val a = intArrayOf(
                0, 0, 0, 1, 1, 1, 0, 1, 1,
                1, 1, 0, 1, 1, 1, 0, 0, 0,
                0, 0, 0, 0, 0, 4, 5, 6, 2,
                1, 1, 1, 0, 0, 0, 0)
        val b = excludeShuntsu(a)
        val c = intArrayOf(
                0, 0, 0, 0, 0, 0, 0, 1, 1,
                1, 1, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1, 1,
                1, 1, 1, 0, 0, 0, 0)
        for(i in 0..b.size - 1) {
            print("${b[i]}, ")
            assert(b[i] == c [i])
        }
    }

    @Test
    fun testExcludeDoitsu() {
        val a = intArrayOf(
                3, 0, 0, 4, 1, 2, 0, 1, 1,
                1, 1, 0, 1, 1, 1, 0, 1, 3,
                0, 0, 0, 0, 0, 4, 3, 2, 2,
                2, 1, 2, 0, 0, 4, 0)
        val b = excludedoitsu(a)
        val c = intArrayOf(
                0, 0, 0, 1, 1, 2, 0, 1, 1,
                1, 1, 0, 1, 1, 1, 0, 1, 0,
                0, 0, 0, 0, 0, 1, 0, 2, 2,
                2, 1, 2, 0, 0, 1, 0)
        for(i in 0..b.size - 1) {
            print("${b[i]}, ")
            assert(b[i] == c [i])
        }
    }

    @Test
    fun testExcludeCorrelation() {
        val a = intArrayOf(
                3, 0, 0, 4, 1, 2, 0, 1, 1,
                1, 1, 0, 1, 1, 1, 0, 1, 3,
                0, 0, 0, 0, 0, 4, 3, 2, 2,
                2, 1, 2, 0, 0, 4, 0)
        val b = exclude2Correlation(a)
        val c = intArrayOf(
                1, 0, 0, 1, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 0, 0)
        for (i in 0..b.first.size - 1) {
            print("${b.second[i]}, ")
        }
        println()
        for (i in 0..b.first.size - 1) {
            print("${b.first[i]}, ")
            assert(b.first[i] == c [i])
        }
    }

    @Test
    fun testGeiKey() {
        val a = intArrayOf(
                3, 0, 0, 4, 1, 2, 0, 1, 1,
                1, 1, 0, 1, 1, 1, 0, 1, 3,
                0, 0, 0, 0, 0, 4, 3, 2, 2,
                2, 1, 2, 0, 0, 4, 0)
        val b = getUseless(a)
        val c = intArrayOf(
                0, 0, 0, 0, 0, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 0, 0, 1, 0,
                0, 0, 0, 0, 0, 1, 0, 0, 0,
                0, 1, 0, 0, 0, 1, 0
        )
        for (i in 0..b.first.size - 1) {
            print("${b.second[i]}, ")
        }
        println()
        for (i in 0..b.first.size - 1) {
            print("${b.first[i]}, ")
            assert(b.first[i] == c [i])
        }
    }

    @Test
    fun daHai() {
        for (loop in 0..100) {
            val tehai = Tehai()
            val mgr = HaiMgr()
            for (i in 0..2) {
                tehai.put(mgr.haiPai())
            }
            tehai.put(mgr.getHai())
            tehai.put(mgr.getHai())
            tehai.sort()
            println(tehai)

            print("sute: ")
            val u = getUseless(tehai.toTypedArray(false))
            for (i in 0..u.first.size - 1) {
                //print("${u.first[i]}, ")
                if (0 == u.first[i]) continue
                print("${Hai.newInstance(i + 1)},")
            }
            println()
            print("want: ")
            for (i in 0..u.second.size - 1) {
                if (0 == u.second[i]) continue
                print("${Hai.newInstance(i + 1)},")
            }
            println()

            var resultId = 0
            var efficiency = EfficiencyKey()
            val list = mutableListOf<Pair<EfficiencyKey, Int>>()
            for (i in 0..u.first.size - 1) {
                if (u.first[i] < 1) continue
                val e = efficiency(tehai.toTypedArray(false), i + 1)
                list.add(Pair(e, i + 1))
                if (e.efficiency > efficiency.efficiency) {
                    efficiency = e
                    resultId = i + 1
                }
            }
            println("da: ${Hai.newInstance(resultId)}")
            for ((e, id) in list) {
                println("hai: ${Hai.newInstance(id)}, efficiency: ${e.efficiency}")
                for (h in e.keys) {
                    println("   >${Hai.newInstance(h)}, ")
                }
            }
            println()
        }
    }
}

