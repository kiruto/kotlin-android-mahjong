/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.models

/**
 * Created by yuriel on 8/13/16.
 */
interface RoundContextV2: RoundContext {
    fun getPlayerContext(player: PlayerModel): PlayerContext
    fun getAllVisibleHai(): List<Hai>
}