/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 yuriel<yuriel3183@gmail.com>
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
 * SOFTWARE.
 */

package dev.yuriel.kotmahjan.models

import dev.yuriel.kotmahjan.models.collections.Mentsu


/**
 * Created by yuriel on 7/23/16.
 */

open class MahjanException(message: String) : Exception(message) {
    open val advice: String = ""
    override val message: String?
        get() = super.message + ". " + advice
}

class HandsOverFlowException : MahjanException("多牌です")

/**
 * 面子の組が和了の形になっていない場合に投げられます
 */
class IllegalMentsuSizeException(
        /**
         * @return 誤っていると判定されている面子の組を返します
         */
        val mentsuList: List<Mentsu>) : MahjanException("面子の組が和了の形になっていません") {

    override val advice: String
        get() = "面子の数は合計で5個もしくは七対子の場合のみ7個でなければなりませんが" + mentsuList.size + "個の面子が見つかりました"
}

class IllegalShuntsuIdentifierException(private val tile: Hai) : MahjanException("順子識別牌としてありえない牌を検出しました") {

    override val advice: String
        get() {
            val entry = "${tile.id}を識別牌として保存しようとしました\n"
            if (tile.num == -1) {
                return entry + "字牌は順子になりえません"
            }
            return entry + "2番目の牌を順子識別牌とするため、1・9牌は識別牌になりえません"
        }
}

class MahjongTileOverFlowException(//Tile.code
        private val code: Int, //何枚見つかり不正なのか
        private val num: Int) : MahjanException("麻雀の牌は4枚までしかありません") {

    override val advice: String
        get() = "(code = " + code + ")が" + num + "枚見つかりました"
}

class NoSuchTileException(private val id: Int): MahjanException("牌は見つかりません") {
    override val advice: String = "id($id)は 1 から 34 までの数字でなければなりません"
}

class ParseHaiException(private val string: String): MahjanException("牌は見つかりません") {
    override val advice: String = "string = " + string + "は牌に転換できません"
}

class DoraCannotFoundException(private val hai: Hai): MahjanException("ドラが見つかりまえん") {
    override val advice: String = "Hai = " + hai
}

class IllegalIntArrayException(private val size: Int): MahjanException("手牌の形が違います") {
    override val advice: String = "(size = $size)"
}

class IllegalIntKazeException(private val index: Int): MahjanException("風のインデックスは違います。") {
    override val advice: String = "(index = $index)"
}

class PlayerNotReadyException: MahjanException("PlayerContext not ready")

class UnbelievableException: MahjanException("バカなっ！！")

class UnexpectedActionExcept(val action: Int): MahjanException("動作を認識できません") {
    override val advice: String = "(action = $action)"
}