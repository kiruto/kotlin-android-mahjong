/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.mahjan.group

import dev.yuriel.kotmvp.Dev
import dev.yuriel.mahjan.actor.LeftTilePlaceHolderActor

/**
 * Created by yuriel on 8/6/16.
 */
class LeftSideGroup : TileGroup<LeftTilePlaceHolderActor>() {
    override fun getOrigin(): Pair<Float, Float> = Pair(20F * Dev.UX, Dev.MAX_Y * Dev.UY - 30F * Dev.UY)
    override fun factory() = LeftTilePlaceHolderActor()
}