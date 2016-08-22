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
import dev.yuriel.kotmahjan.models.toTypedHaiArray
import rx.Observable
import rx.android.schedulers.AndroidSchedulers

/**
 * Created by yuriel on 8/13/16.
 */
abstract class TileCollection {

    protected val listeners = mutableMapOf<Int, (List<Hai>) -> Unit>()
    protected abstract val haiListStore: MutableList<Hai>
    val haiList: List<Hai> by lazy { haiListStore }

    fun listen(id: Int, listener: (List<Hai>) -> Unit) {
        listeners.put(id, listener)
    }

    protected fun notifyDataChange() {
        synchronized(haiListStore) {
            Observable.just(haiListStore)
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { list ->
                        for ((id, l) in listeners) {
                            l.invoke(list)
                        }
                    }
                    .subscribe()
        }
    }

    fun remove(hai: Hai) {
        for (h in haiList) {
            if (h == hai) {
                haiListStore.remove(hai)
                notifyDataChange()
                return
            }
        }
    }

    /**
     * [0,0,0,0,0,0,0,0,0,  MZ
     *  0,0,0,0,0,0,0,0,0,  PZ
     *  0,0,0,0,0,0,0,0,0,  SZ
     *  0,0,0,0,0,0,0]      TSUHAI
     */
    fun toTypedArray(removeFuro:Boolean = true): IntArray = toTypedHaiArray(haiList, removeFuro)
}