/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.mahjan.group

import dev.yuriel.kotmahjan.models.TsumoHaiModel
import dev.yuriel.kotmvp.Dev
import dev.yuriel.mahjan.actor.TilePlaceHolderActor

/**
 * Created by yuriel on 8/5/16.
 */
class HandsGroup : TileGroup<TilePlaceHolderActor>() {
    override fun getOrigin(): Pair<Float, Float> = Pair(50F * Dev.UX, 0F)
    override fun factory(position: Int, isTsumo: Boolean) = TilePlaceHolderActor(position, isTsumo)
}