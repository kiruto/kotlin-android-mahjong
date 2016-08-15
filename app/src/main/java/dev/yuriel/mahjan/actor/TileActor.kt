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

package dev.yuriel.mahjan.actor

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmvp.bases.BaseActor
import dev.yuriel.mahjan.model.TileWrapper

/**
 * Created by yuriel on 8/6/16.
 */
abstract class TileActor(val isTsumo: Boolean = false): BaseActor(), Comparable<TilePlaceHolderActor> {
    abstract fun getTileSize(): Pair<Float,  Float>
    abstract fun getTileOrigin(): Pair<Float, Float>
    abstract fun getTilePosition(): Int
    abstract fun setTilePosition(value: Int)

    var texture: TextureRegion? = null
        get() = tile?.texture
        private set

    var back: TextureRegion? = null
        get() = tile?.back
        private set

    var obverse: TextureRegion? = null
        get() =tile?.obverse

    var tile: TileWrapper? = null
        set(value) {
            field = value

        }
    fun update(hai: Hai?) {
        if (isTsumo) {
            resetPosition()
        }
        if (tile == null)
            tile = TileWrapper()
        tile?.hai = hai
    }

    fun resetPosition() {
        val size = getTileSize()
        setSize(size.first, size.second)
        val origin = getTileOrigin()
        setPosition(origin.first, origin.second)
        setOrigin(origin.first, origin.second)
    }

    override fun destroy() {
        tile?.destroy()
        tile = null
    }

    override fun compareTo(other: TilePlaceHolderActor): Int = getTilePosition() - other.getTilePosition()
}