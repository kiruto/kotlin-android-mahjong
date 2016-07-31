/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.models.PlayerContext
import dev.yuriel.kotmahjan.models.RoundContext

/**
 * Created by yuriel on 7/24/16.
 */
fun 槍槓Impl(r: RoundContext?, p: PlayerContext?, a: Any): Boolean = p?.isChankan()?: false