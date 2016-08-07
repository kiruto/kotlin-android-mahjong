package dev.yuriel.mahjan.group

import dev.yuriel.kotmvp.Dev
import dev.yuriel.mahjan.actor.RightTilePlaceHolderActor

/**
 * Created by yuriel on 8/7/16.
 */
class RightSideGroup: TileGroup<RightTilePlaceHolderActor>() {
    override val origin: Pair<Float, Float> = Pair(Dev.MAX_X * Dev.UX - 50 * Dev.UX, 0F)
    override fun factory() = RightTilePlaceHolderActor()
}