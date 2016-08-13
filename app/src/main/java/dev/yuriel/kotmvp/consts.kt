/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
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