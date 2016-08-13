/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.models.collections

import dev.yuriel.kotmahjan.models.Hai

/**
 * Created by yuriel on 8/13/16.
 */


/**
 * 槓子に関するクラスです
 * 暗槓と明槓の両方を扱います
 */
class Kantsu(override var identifierTile: Hai?): Mentsu() {

    /**
     * 槓子が完成していることを前提にしているため
     * 槓子であるかのチェックは伴いません。
     * @param isOpen         暗槓の場合false, 明槓の場合はtrueを入れて下さい
     * @param identifierTile どの牌の槓子なのか
     */
    constructor(isOpen: Boolean, identifierTile: Hai): this(identifierTile) {
        this.isOpen = isOpen
        this.isMentsu = true
    }

    /**
     * 槓子であるかのチェックも伴います
     * すべての牌(t1~4)が同じ場合にisMentsuがtrueになります
     * @param isOpen 暗槓の場合false, 明槓の場合はtrueを入れて下さい
     * @param tile1  1枚目
     * @param tile2  2枚目
     * @param tile3  3枚目
     * @param tile4  4枚目
     */
    constructor(isOpen: Boolean, tile1: Hai, tile2: Hai, tile3: Hai, tile4: Hai): this(tile1) {
        this.isOpen = isOpen
        this.isMentsu = check(tile1, tile2, tile3, tile4)
        if (!isMentsu) {
            identifierTile = null
        }
    }

    override val fu: Int by lazy {
        var mentsuFu = 8
        if (!isOpen) {
            mentsuFu *= 2
        }
        if (identifierTile?.isYaochu() == true) {
            mentsuFu *= 2
        }
        mentsuFu
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Kantsu) return false

        if (isMentsu !== o.isMentsu) return false
        if (isOpen !== o.isOpen) return false
        return identifierTile === o.identifierTile

    }

    override fun hashCode(): Int {
        var result: Int = if (null != identifierTile) identifierTile!!.hashCode() else 0
        result = 31 * result + if (isMentsu) 1 else 0
        result = 31 * result + if (isOpen) 1 else 0
        return result
    }

    companion object {

        /**
         * t1~4が同一の牌かを調べます
         * @param tile1 1枚目
         * @param tile2 2枚目
         * @param tile3 3枚目
         * @param tile4 4枚目
         * @return 槓子の場合true 槓子でない場合false
         */
        fun check(tile1: Hai, tile2: Hai, tile3: Hai, tile4: Hai): Boolean {
            return tile1 sameAs tile2 && tile2 sameAs tile3 && tile3 sameAs tile4
        }
    }
}