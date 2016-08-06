package dev.yuriel.mahjan.stage

import com.badlogic.gdx.Gdx
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.mahjan.actor.TilePlaceHolderActor
import dev.yuriel.kotmvp.bases.BaseStage
import java.util.*

/**
 * Created by yuriel on 8/5/16.
 */
class HandsStage: BaseStage() {
    val tileList: Array<TilePlaceHolderActor>
    init {
        tileList = Array(14) { i ->
            val actor = TilePlaceHolderActor()
            actor.setPosition(actor.width * i, 0F)
            actor.position = i
            this.addActor(actor)
            actor
        }
    }

    fun updateList(haiList: List<Hai>) {
        for (index in 0..tileList.size - 1) {
            val actor = tileList[index]
            if (haiList.size - 1 > index) {
                actor.update(haiList[index])
            } else {
                actor.update(null)
            }
            actor.setPosition(actor.width * index, 0F)
        }
    }

    fun sort() {
        tileList.sort()
    }

    override fun destroy() {
        for (i in tileList) {
            i.destroy()
        }
    }
}