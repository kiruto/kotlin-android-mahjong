/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.mahjan.group

import dev.yuriel.mahjan.actor.OppoTilePlaceHolderActor

/**
 * Created by yuriel on 8/8/16.
 */
class OpposideGroup: TileGroup<OppoTilePlaceHolderActor>() {
    override fun getOrigin(): Pair<Float, Float> = Pair(0F, 0F)
    override fun factory(): OppoTilePlaceHolderActor = OppoTilePlaceHolderActor()
}