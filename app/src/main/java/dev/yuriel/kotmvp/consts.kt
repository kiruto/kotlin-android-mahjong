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

package dev.yuriel.kotmvp

import android.view.View

/**
 * Created by yuriel on 8/5/16.
 */
const val INVISIBLE = View.INVISIBLE
const val VISIBLE = View.VISIBLE
const val GONE = View.GONE

const val TILE_WIDTH = 33F * 1.2F
const val TILE_HEIGHT = 45F * 1.2F

private const val SCALE = 0.45F
private const val FURO_SCALE = 0.55F

const val SMALL_TILE_WIDTH = TILE_WIDTH * SCALE
const val SMALL_TILE_HEIGHT = TILE_HEIGHT * SCALE
const val SMALL_TILE_MARGIN = SMALL_TILE_WIDTH * 0.5F

const val FURO_TILE_WIDTH = TILE_WIDTH * FURO_SCALE
const val FURO_TILE_HEIGHT = TILE_HEIGHT * FURO_SCALE

const val SIDE_TILE_WIDTH = TILE_WIDTH * SCALE
const val SIDE_TILE_HEIGHT = TILE_HEIGHT * SCALE / 2F

const val TABLE_AREA_WIDTH = SIDE_TILE_WIDTH * 22F
const val TABLE_AREA_HEIGHT = SIDE_TILE_WIDTH * 14F

const val LEFT_SIDE = 0x10
const val RIGHT_SIDE = 0x20