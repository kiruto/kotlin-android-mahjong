package dev.yuriel.kotmahjan.models

import java.util.*

/**
 * Created by yuriel on 7/16/16.
 */

class Yama(val haiList: MutableList<Hai>) {

}

class Kawa {
    private val haiList: MutableList<Hai> by lazy { ArrayList<Hai>() }

    fun push(hai: Hai) = haiList.add(hai)

    fun pop(): Hai {
        val hai = haiList.get(haiList.size - 1)
        haiList.remove(hai)
        return hai
    }

    fun get(): MutableList<Hai> = haiList

    fun clear() {
        haiList.clear()
    }
}

class Tehai: Comparator<Hai> {
    val haiList: MutableList<Hai> by lazy {
        ArrayList<Hai>()
    }

    fun clear() {
        haiList.clear()
    }

    fun enouph(): Boolean {
        return haiList.size > 12
    }

    fun sort() {
        //val result: ArrayList<Hai> = ArrayList()
        haiList.sortWith(this)
        val resultArray: Array<ArrayList<Hai>> = Array(HaiType.values().size, { i -> ArrayList<Hai>() })
        for (hai: Hai in haiList) {
            for (t: HaiType in HaiType.values()) {
                if (hai.type == t) {
                    resultArray[t.ordinal].add(hai)
                    continue;
                }
            }
        }
        haiList.clear()
        for (list in resultArray) {
            haiList.addAll(list)
        }
    }

    override fun compare(hai1: Hai, hai2: Hai): Int {
        return hai2.num - hai1.num
    }
}

open class HaiGroup
class Chi(val h1: Hai, h2: Hai, h3: Hai): HaiGroup()
class Pon(val h1: Hai, h2: Hai, h3: Hai): HaiGroup()
class Kan(val h1: Hai, h2: Hai, h3: Hai, val h4: Hai): HaiGroup()


/**
 * 副露
 */
class Furo {
    val group: MutableList<HaiGroup> by lazy {
        ArrayList<HaiGroup>()
    }

    fun add(group: HaiGroup) {
        this.group.add(group)
    }

    fun clear() {
        group.clear()
    }
}