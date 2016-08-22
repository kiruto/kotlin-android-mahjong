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

package dev.yuriel.kotmahjan.ai

import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.PlayerCommander
import dev.yuriel.kotmahjan.models.fromTypedHaiArray
import dev.yuriel.kotmahjan.models.toTypedHaiArray
import kotlin.properties.Delegates

/**
 * Created by yuriel on 7/30/16.
 */

abstract class AI: PlayerCommander {
    abstract fun store(haiList: List<Hai>)
    abstract fun clear()
    abstract fun getHai(): List<Hai>
    abstract fun getHaiRaw(): String
    abstract fun da(haiList: List<Hai>, basis: List<Hai>): Hai
    abstract fun remove(hai: Hai)

    private var listener: Array<out AISaid>? = null

    protected var msg: String by Delegates.observable("") { prop, old, new -> say(new) }

    override fun da(basis: List<Hai>): Hai {
        return da(getHai(), basis)
    }

    fun setListener(vararg listener: AISaid) {
        this.listener = listener
    }

    protected fun say(str: String) {
        for (l in listener?: return) {
            l.heard(str)
        }
    }

    override fun getBasisByVisibleHai(visible: List<Hai>): List<Hai> {
        val visibleArray = toTypedHaiArray(visible)
        val resultArray = IntArray(34) { i -> 4 - visibleArray[i] }
        return fromTypedHaiArray(resultArray)
    }
}