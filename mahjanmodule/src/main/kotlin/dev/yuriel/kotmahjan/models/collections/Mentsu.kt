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