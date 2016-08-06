package dev.yuriel.mahjan.group

import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmvp.bases.BaseGroup
import dev.yuriel.mahjan.actor.TileActor

/**
 * Created by yuriel on 8/6/16.
 */
abstract class TileGroup: BaseGroup() {
    abstract val tileList: Array<out TileActor>
    //abstract fun updateList(haiList: List<Hai>)

    fun updateList(haiList: List<Hai>) {
        for (index in 0..tileList.size - 1) {
            val actor = tileList[index]
            if (haiList.size - 1 >= index) {
                actor.update(haiList[index])
            } else {
                actor.update(null)
            }
        }
    }

    override fun destroy() {
        for (i in tileList) {
            i.destroy()
        }
    }
}