package dev.yuriel.mahjan.stage

import android.nfc.Tag
import android.service.quicksettings.Tile
import android.util.Log
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Logger
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.mahjan.actor.TilePlaceHolderActor
import dev.yuriel.kotmvp.bases.BaseStage
import dev.yuriel.mahjan.texture.TileMgr
import java.util.*

/**
 * Created by yuriel on 8/5/16.
 */
class HandsStage: BaseStage() {
    val tileList: Array<TilePlaceHolderActor>
    init {
        TileMgr.load()
        tileList = Array(13) { i ->
            val actor = TilePlaceHolderActor()
            actor.setPosition(actor.width * i, 0F)
            actor.position = i
            actor.addListener(object: InputListener(){
                override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {

                    return true
                }
            })
            addActor(actor)
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