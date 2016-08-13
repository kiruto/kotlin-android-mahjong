/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmvp.bases

import com.badlogic.gdx.Game
import dev.yuriel.kotmvp.Dev

/**
 * Created by yuriel on 8/3/16.
 */
abstract class BaseGame: Game() {

    override fun create() {
        Dev.game = this
    }
}