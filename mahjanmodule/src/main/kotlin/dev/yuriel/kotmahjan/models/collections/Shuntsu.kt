/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.models.collections

import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.IllegalShuntsuIdentifierException

/**
 * Created by yuriel on 8/13/16.
 * 順子に関するクラスです
 * 暗順子と明順子の両方を扱います
 */
class Shuntsu(override var identifierTile: Hai?) : Mentsu() {

    /**
     * 順子であることがわかっている場合に利用します
     * @param isOpen         明順子ならばtrue 暗順子ならばfalse
     * @param identifierTile 順子の組の二番目
     */
    @Throws(IllegalShuntsuIdentifierException::class)
    constructor(isOpen: Boolean, identifierTile: Hai): this(identifierTile) {
        setIdTile(identifierTile)
        this.isOpen = isOpen
        this.isMentsu = true
    }

    /**
     * 順子であるかの判定も伴います
     * @param isOpen 明順子ならばtrue 暗順子ならばfalseを入力して下さい
     * @param tile1  1枚目
     * @param tile2  2枚目
     * @param tile3  3枚目
     */
    constructor(isOpen: Boolean, tile1: Hai, tile2: Hai, tile3: Hai): this(tile2) {
        var t1 = tile1
        var t2 = tile2
        var t3 = tile3
        this.isOpen = isOpen

        // TODO: 共通化
        //ソートする
        var s: Hai
        if (t1.num > t2.num) {
            s = t1
            t1 = t2
            t2 = s
        }
        if (t1.num > t3.num) {
            s = t1
            t1 = t3
            t3 = s
        }
        if (t2.num > t3.num) {
            s = t2
            t2 = t3
            t3 = s
        }
        this.isMentsu = check(t1, t2, t3)
        if (!isMentsu) {
            identifierTile = null
        }
    }

    @Throws(IllegalShuntsuIdentifierException::class)
    private fun setIdTile(identifierTile: Hai) {
        if (identifierTile.isYaochu()) {
            throw IllegalShuntsuIdentifierException(identifierTile)
        }
        this.identifierTile = identifierTile
    }

    override val fu: Int
        get() = 0

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Shuntsu) return false

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
         * 順子かどうかの判定を行ないます
         * @param t1 1枚目
         * @param t2 2枚目
         * @param t3 3枚目
         * @return 順子であればtrue 順子でなければfalse
         */
        fun check(t1: Hai, t2: Hai, t3: Hai): Boolean {
            var tile1 = t1
            var tile2 = t2
            var tile3 = t3

            //Typeが違う場合false
            if (tile1.type != tile2.type || tile2.type != tile3.type) {
                return false
            }

            //字牌だった場合false
            if (tile1.num == -1 || tile2.num == -1 || tile3.num == -1) {
                return false
            }

            //ソートする
            var s: Hai
            if (tile1.num > tile2.num) {
                s = tile1
                tile1 = tile2
                tile2 = s
            }
            if (tile1.num > tile3.num) {
                s = tile1
                tile1 = tile3
                tile3 = s
            }
            if (tile2.num > tile3.num) {
                s = tile2
                tile2 = tile3
                tile3 = s
            }

            //判定
            return tile1.num + 1 == tile2.num && tile2.num + 1 == tile3.num
        }
    }
}