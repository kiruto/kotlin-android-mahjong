package dev.yuriel.mahjan.group

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmvp.Dev
import dev.yuriel.kotmvp.bases.BaseGroup
import dev.yuriel.mahjan.actor.TilePlaceHolderActor
import dev.yuriel.mahjan.texture.TileMgr

/**
 * Created by yuriel on 8/5/16.
 */
class HandsGroup : TileGroup() {
    override val tileList: Array<TilePlaceHolderActor>

    init {
        TileMgr.load()
        tileList = Array(13) { i ->
            val actor = TilePlaceHolderActor()
            actor.position = i
            actor.addListener(object: InputListener(){
                override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {

                    return true
                }
            })
            addActor(actor)
            actor
        }
        setPosition(50F * Dev.UX, 0F)
    }
}