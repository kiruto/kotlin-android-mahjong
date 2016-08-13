/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.mahjan.texture

/**
 * Created by yuriel on 8/6/16.
 */
interface TextureMgr {
    fun load(): Boolean
    fun destroy()
}