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
import dev.yuriel.kotmahjan.models.MahjongTileOverFlowException
import java.util.*

/**
 * Created by yuriel on 8/13/16.
 * 対子であることがわかっている場合に使います
 * @param identifierTile 対子の種類
 */
class Toitsu(override var identifierTile: Hai?) : Mentsu() {

    init {
        this.isMentsu = true
    }

    /**
     * 対子であるかのチェックを伴います
     * @param tile1 1枚目
     * @param tile2 2枚目
     */
    constructor(tile1: Hai, tile2: Hai): this(tile1) {
        isMentsu = Toitsu.check(tile1, tile2)
        if (!isMentsu) {
            this.identifierTile = null
        }
    }

    override val fu: Int
        get() = 0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Toitsu) return false

        if (isMentsu !== other.isMentsu) return false
        return identifierTile === other.identifierTile

    }

    override fun hashCode(): Int {
        var result: Int = if (identifierTile != null) identifierTile!!.hashCode() else 0
        result = 31 * result + if (isMentsu) 1 else 0
        return result
    }

    companion object {

        /**
         * @param tile1 1枚目
         * @param tile2 2枚目
         * @return 2枚が一致すればtrue
         */
        fun check(tile1: Hai, tile2: Hai): Boolean {
            return tile1 sameAs tile2
        }

        /**
         * 対子になりうる牌をリストにして返す
         * @param tiles 手牌
         * @return 雀頭候補の対子リスト
         */
        @Throws(MahjongTileOverFlowException::class)
        fun findJantoCandidate(tiles: IntArray): List<Toitsu> {
            val result = ArrayList<Toitsu>(7)
            for (i in tiles.indices) {
                if (tiles[i] > 4) {
                    throw MahjongTileOverFlowException(i, tiles[i])
                }
                if (tiles[i] >= 2) {
                    result.add(Toitsu(Hai.factory.newInstance(i)))
                }
            }
            return result
        }
    }
}