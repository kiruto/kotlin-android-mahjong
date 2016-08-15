/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.mahjan.interfaces

import dev.yuriel.mahjan.group.HandsGroup

/**
 * Created by yuriel on 8/13/16.
 */
interface MainScreenPresenter {
    fun startRound()
    fun getActionListener(): HandsGroup.Listener
}