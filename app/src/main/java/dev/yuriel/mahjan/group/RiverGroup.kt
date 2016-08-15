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

package dev.yuriel.mahjan.group

import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmvp.bases.BaseGroup
import dev.yuriel.mahjan.model.TileWrapper

/**
 * Created by yuriel on 8/9/16.
 */
class RiverGroup(): BaseGroup() {

    private val lines: Array<TileHorizontalGroup>
    private var tiles: List<TileWrapper>? = null

    init {
        lines = Array(3) { i ->
            val result = TileHorizontalGroup(mutableListOf(), i)
            addActor(result)
            result
        }
    }

    fun update(tile: List<Hai>) {
        val list = Array(tile.size) { i -> TileWrapper(tile[i]) }.toList()
        if (list.size <= tiles?.size?: Int.MAX_VALUE) {
            resetTiles(list)
        } else if (list.size - tiles!!.size == 1) {
            val targetLine = if (list.size <= 6) 0 else if (list.size <= 12) 1 else 2
            lines[targetLine].append(list.last())
        }
        tiles = list
    }

    private fun resetTiles(tile: List<TileWrapper>) {
        var index = -1
        var subList: MutableList<TileWrapper>? = null
        for (i in 0..tile.size - 1) {
            if (i % 6 == 0) {
                if (index > -1 && index < 2 && subList!= null) {
                    lines[index].update(subList)
                }
                if (index < 2) {
                    index++
                    subList = mutableListOf()
                }
            }
            subList?.add(tile[i])
        }
        if (subList != null) {
            lines[index].update(subList)
        }
        calculate()
    }

    private fun append(tile: TileWrapper, line: Int) {

    }

    fun calculate() {
        var originY = 0F
        for (i in lines) {
            i.setPosition(0F, originY)
            originY -= i.height
        }
    }

    override fun destroy() {
        for (i in lines) {
            i.destroy()
        }
    }

    private fun TileHorizontalGroup.isFull(): Boolean = size == 6
}