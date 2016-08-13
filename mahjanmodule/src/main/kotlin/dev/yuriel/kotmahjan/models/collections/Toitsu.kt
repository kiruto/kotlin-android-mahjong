/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.models.collections

import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.MahjongTileOverFlowException
import java.util.*

/**
 * Created by yuriel on 8/13/16.
 */

class Toitsu(override var identifierTile: Hai?) : Mentsu() {

    /**
     * 対子であることがわかっている場合に使います
     * @param identifierTile 対子の種類
     */
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

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Toitsu) return false

        if (isMentsu !== o.isMentsu) return false
        return identifierTile === o.identifierTile

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