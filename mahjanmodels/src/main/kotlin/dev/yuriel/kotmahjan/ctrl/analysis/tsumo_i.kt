package dev.yuriel.kotmahjan.ctrl.analysis

import dev.yuriel.kotmahjan.models.*
import dev.yuriel.kotmahjan.models.toTypedArray

/**
 * Created by yuriel on 7/30/16.
 * 第一号ロボット：イ
 * 鳴かないながらツモだけ麻雀プレイします
 */
class I: AI() {
    private var tehai: Tehai = Tehai()

    override fun receive(hai: Hai) {
        tehai.put(hai)
        tehai.sort()
    }

    override fun da(haiList: List<Hai>, basis: List<Hai>): Hai {
        var resultHai: Hai
        var u = getUselessGeneralized(tehai.toTypedArray(false))
        var result = printResultByGen(u, tehai, false)
        if (result.first != 0) {
            resultHai = Hai.newInstance(result.first)
            println("da: $resultHai")
        } else {
            u = getUselessSpecialized(tehai.toTypedArray(false))
            result = printResultByGen(u, tehai, true)
        }

        if (result.first != 0) {
            resultHai = Hai.newInstance(result.first)
            println("da: $resultHai")
        } else {
            val array = toTypedArray(basis)
            val b = sortEffectInRange(u, tehai.toTypedArray(), array)
            resultHai = Hai.newInstance(b.g2kList[0].group[0])
            println("extreme da: $resultHai")

        }

        for ((e, id) in result.second) {
            println("hai: ${Hai.newInstance(id)}, efficiency: ${e.efficiency}")
            for (h in e.keys) {
                println("   >${Hai.newInstance(h)}, ")
            }
        }
        println()
        return resultHai
    }

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

    private fun printResultByGen(u: Useless2Key2KeyMap, tehai: Tehai, hl: Boolean = false):
            Pair<Int, List<Pair<Efficiency2Key, Int>>> {
        print("${if (hl) ANSI_YELLOW else ANSI_CYAN}")
        print("sute: ")
        for (i in 0..u.useless.size - 1) {
            //print("${u.first[i]}, ")
            if (0 == u.useless[i]) continue
            print("${Hai.newInstance(i + 1)},")
        }
        println()
        println("want: ")
        for (i in 0..u.keys.size - 1) {
            if (0 == u.keys[i]) continue
            print("   >${Hai.newInstance(i + 1)}, ")
            print("Because: ")
            for (b in u.k2gMap[i + 1]!!) {
                print("${Hai.newInstance(b)},")
            }
            println()
        }
        println()

        var resultId = 0
        var efficiency = Efficiency2Key()
        val list = mutableListOf<Pair<Efficiency2Key, Int>>()
        for (i in 0..u.useless.size - 1) {
            if (u.useless[i] < 1) continue
            val e = efficiency(tehai.toTypedArray(false), i + 1)
            list.add(Pair(e, i + 1))
            if (e.efficiency > efficiency.efficiency) {
                efficiency = e
                resultId = i + 1
            }
        }
        print(ANSI_RESET)
        return Pair(resultId, list)
    }
}