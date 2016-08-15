/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 yuriel
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *
 */

package dev.yuriel.kotmahjan.rules

import dev.yuriel.kotmahjan.models.*
import dev.yuriel.kotmahjan.models.collections.*
import java.util.*

/**
 * Created by yuriel on 7/23/16.
 * 上がれる面子を整理したクラスです
 */
class MentsuSupport
/**
 * @param mentsuList 上がりとなった面子のリスト
 * @param last
 * @throws IllegalMentsuSizeException 和了れる形になっていなければthrow
 */
@Throws(IllegalMentsuSizeException::class)
constructor(mentsuList: List<Mentsu>, val last: Hai?) {

    private val toitsuList = ArrayList<Toitsu>(7)
    private val shuntsuList = ArrayList<Shuntsu>(4)
    private val kotsuList = ArrayList<Kotsu>(4)
    private val kantsuList = ArrayList<Kantsu>(4)

    init {
        for (mentsu in mentsuList) {
            setMentsu(mentsu)
        }

        //整合性を確認する
        val checkSum = shuntsuList.size + kotsuList.size + kantsuList.size
        val isNormal = checkSum == 4 && toitsuList.size == 1
        val isChitoitsu = toitsuList.size == 7 && checkSum == 0
        if (!isNormal && !isChitoitsu) {
            throw IllegalMentsuSizeException(mentsuList)
        }
    }

    /**
     * どの面子が入っても対応可能なセッター
     * @param mentsu 入力したい面子
     */
    private fun setMentsu(mentsu: Mentsu) {
        if (mentsu is Toitsu) {
            toitsuList.add(mentsu)
        } else if (mentsu is Shuntsu) {
            shuntsuList.add(mentsu)
        } else if (mentsu is Kotsu) {
            kotsuList.add(mentsu)
        } else if (mentsu is Kantsu) {
            kantsuList.add(mentsu)
        }
    }

    /**
     * 七対子の場合はnullを返します
     * @return 雀頭を返します
     */
    val janto: Toitsu?
        get() {
            if (toitsuCount == 1) {
                return toitsuList[0]
            }
            return null
        }

    fun getToitsuList(): List<Toitsu> {
        return toitsuList
    }

    /**
     * 対子の数を返します
     * 1もしくは7以外を返すことはありません
     * @return 通常の型の場合1 七対子の型の場合7
     */
    val toitsuCount: Int
        get() = toitsuList.size

    fun getShuntsuList(): List<Shuntsu> {
        return shuntsuList
    }

    /**
     * 順子の数を返します
     * 0~4のどれかです
     * @return 順子の数
     */
    val shuntsuCount: Int
        get() = shuntsuList.size

    fun getKotsuList(): List<Kotsu> {
        return kotsuList
    }

    /**
     * 刻子でも槓子でも役の判定に影響しない場合に利用します
     * 刻子と槓子をまとめて返します。
     * TODO:good name
     * @return 刻子と槓子をまとめたリスト
     */
    val kotsuKantsu: List<Kotsu>
        get() {
            val kotsuList = ArrayList<Kotsu>(this.kotsuList)
            for (kantsu in kantsuList) {
                kotsuList.add(Kotsu(kantsu.isOpen, kantsu.getHai()))
            }
            return kotsuList
        }

    /**
     * 刻子の数を返します
     * 0~4のどれかです
     * @return 刻子の数
     */
    val kotsuCount: Int
        get() = kotsuList.size

    fun getKantsuList(): List<Kantsu> {
        return kantsuList
    }

    /**
     * 槓子の数を返します
     * 0~4のどれかです
     * @return 槓子の数
     */
    val kantsuCount: Int
        get() = kantsuList.size

    /**
     * 対子も含めて全ての面子をリストにして返します
     * @return 構成する全ての面子のリスト
     */
    val allMentsu: List<Mentsu>
        get() {
            val allMentsu = ArrayList<Mentsu>(7)
            allMentsu.addAll(getToitsuList())
            allMentsu.addAll(getShuntsuList())
            allMentsu.addAll(getKotsuList())
            allMentsu.addAll(getKantsuList())

            return allMentsu
        }

    fun isRyanmen(last: Hai): Boolean {
        for (shuntsu in shuntsuList) {
            if (shuntsu.isOpen) {
                continue
            }
            if (shuntsu.getHai()?.type != last.type) {
                continue
            }

            val number = shuntsu.getHai()?.num
            if (number == 8 || number == 2) {
                continue
            }

            if (number == last.num + 1 || number == last.num - 1) {
                return true
            }
        }

        return false
    }

    fun isTanki(last: Hai): Boolean {
        return janto!!.getHai()?.sameAs(last) == true
    }

    fun isKanchan(last: Hai): Boolean {
        if (isRyanmen(last)) {
            return false
        }
        for (shuntsu in shuntsuList) {
            if (shuntsu.isOpen || shuntsu.getHai()?.type != last.type) {
                continue
            }
            if (shuntsu.getHai()?.num == last.num) {
                return true
            }
        }
        return false
    }

    fun isPenchan(last: Hai): Boolean {
        if (isRyanmen(last)) {
            return false
        }
        for (shuntsu in shuntsuList) {
            if (shuntsu.isOpen || shuntsu.getHai()?.type != last.type) {
                continue
            }
            val number = shuntsu.getHai()?.num
            if (number == 8 && last.num == 7) {
                return true
            }
            if (number == 2 && last.num == 3) {
                return true
            }
        }
        return false
    }

    /**
     * 各面子のリストの順番は関係ないので、
     * 面子の順番が違っていてもtrueになります
     * @param o
     * @return
     */
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is MentsuSupport) return false

        if (last !== o.last) return false
        if (toitsuList.size != o.toitsuList.size) return false
        if (shuntsuList.size != o.shuntsuList.size) return false
        if (kotsuList.size != o.kotsuList.size) return false
        if (kantsuList.size != o.kantsuList.size) return false
        for (toitsu in toitsuList) {
            if (!o.toitsuList.contains(toitsu)) return false
        }
        for (shuntsu in shuntsuList) {
            if (!o.shuntsuList.contains(shuntsu)) return false
        }
        for (kotsu in kotsuList) {
            if (!o.kotsuList.contains(kotsu)) return false
        }
        for (kantsu in kantsuList) {
            if (!o.kantsuList.contains(kantsu)) return false
        }

        return true
    }

    override fun hashCode(): Int {
        var tmp = 0
        var result: Int

        result = last?.hashCode()?: 0

        if (toitsuList != null) {
            for (toitsu in toitsuList) {
                tmp += toitsu.hashCode()
            }
        }
        result = 31 * result + tmp

        tmp = 0
        if (shuntsuList != null) {
            for (shuntsu in shuntsuList) {
                tmp += shuntsu.hashCode()
            }
        }

        result = 31 * result + tmp

        tmp = 0
        if (kotsuList != null) {
            for (kotsu in kotsuList) {
                tmp += kotsu.hashCode()
            }
        }

        result = 31 * result + tmp

        tmp = 0
        if (kantsuList != null) {
            for (kantsu in kantsuList) {
                tmp += kantsu.hashCode()
            }
        }

        return 31 * result + tmp
    }
}
