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

package dev.yuriel.kotmahjan.models.collections

import dev.yuriel.kotmahjan.models.Hai

/**
 * Created by yuriel on 8/13/16.
 * 刻子に関するクラスです
 * 暗刻と明刻の両方を扱います
 */
class Kotsu(override var identifierTile: Hai?) : Mentsu() {

    /**
     * 刻子であることがわかっている場合に利用します
     * @param isOpen         暗刻ならばfalse 明刻ならばtrue
     * @param identifierTile どの牌の刻子なのか
     */
    constructor(isOpen: Boolean, identifierTile: Hai?): this(identifierTile) {
        this.isOpen = isOpen
        this.isMentsu = true
    }

    /**
     * 刻子であるかのチェックも伴います
     * すべての牌(t1~3)が同じ場合にisMentsuがtrueになります
     * @param isOpen 暗刻の場合false, 明刻の場合はtrueを入れて下さい
     * @param tile1  1枚目
     * @param tile2  2枚目
     * @param tile3  3枚目
     */
    constructor(isOpen: Boolean, tile1: Hai, tile2: Hai, tile3: Hai): this(tile1) {
        this.isOpen = isOpen
        this.isMentsu = check(tile1, tile2, tile3)
        if (!isMentsu) {
            identifierTile = null
        }
    }

    override val fu: Int
        get() {
            var mentsuFu = 2
            if (!isOpen) {
                mentsuFu *= 2
            }
            if (identifierTile?.isYaochu() == true) {
                mentsuFu *= 2
            }
            return mentsuFu
        }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Kotsu) return false

        if (isMentsu !== o.isMentsu) return false
        if (isOpen !== o.isOpen) return false
        return identifierTile === o.identifierTile

    }

    override fun hashCode(): Int {
        var result: Int = if (identifierTile != null) identifierTile!!.hashCode() else 0
        result = 31 * result + if (isMentsu) 1 else 0
        result = 31 * result + if (isOpen) 1 else 0
        return result
    }

    companion object {

        /**
         * 刻子であるかの判定を行ないます
         * @param tile1 1枚目
         * @param tile2 2枚目
         * @param tile3 3枚目
         * @return 刻子であればtrue 刻子でなければfalse
         */
        fun check(tile1: Hai, tile2: Hai, tile3: Hai): Boolean {
            return tile1 sameAs tile2 && tile2 sameAs tile3
        }
    }
}