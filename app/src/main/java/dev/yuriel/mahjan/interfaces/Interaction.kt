/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.mahjan.interfaces

import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.PlayerModel

/**
 * Created by yuriel on 8/14/16.
 */
interface Interaction {
    fun getPlayerModel(): PlayerModel

    fun waiting4Flod(): Hai

    fun waiting4Kan(): Boolean

    fun waiting4Pon(): Boolean

    fun waiting4Chi(): Boolean

    fun waiting4Ron(): Boolean
}