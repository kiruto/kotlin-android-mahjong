package dev.yuriel.mahjan.group

import dev.yuriel.kotmvp.Dev
import dev.yuriel.mahjan.actor.TilePlaceHolderActor

/**
 * Created by yuriel on 8/5/16.
 */
class HandsGroup : TileGroup<TilePlaceHolderActor>() {
    override val origin: Pair<Float, Float> = Pair(50F * Dev.UX, 0F)
    override fun factory() = TilePlaceHolderActor()
}