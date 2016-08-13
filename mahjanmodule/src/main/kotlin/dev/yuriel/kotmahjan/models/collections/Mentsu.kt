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
abstract class Mentsu {
    /**
     * どの牌で面子となっているか
     * 順子の場合は2番目の牌です
     */
    abstract var identifierTile: Hai?

    /**
     * 面子として成立している場合true
     * 面子として成立していない場合false
     */
    var isMentsu: Boolean = false

    /**
     * 鳴いているか
     *
     * 明X子の場合はtrue
     * 暗X子の場合はfalse
     * 食い下がり判定用です
     * 鳴いている場合はtrueですが、
     * 暗槓の場合はfalseです。
     */
    var isOpen: Boolean = false

    /**
     * 符計算用
     * @return 各面子での加算符
     */
    abstract val fu: Int

    fun getHai(): Hai? {
        return identifierTile
    }
}