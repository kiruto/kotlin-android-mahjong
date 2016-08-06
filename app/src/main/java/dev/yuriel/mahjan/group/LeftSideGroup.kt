package dev.yuriel.mahjan.group

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmvp.Dev
import dev.yuriel.kotmvp.bases.BaseGroup
import dev.yuriel.mahjan.actor.LeftTilePlaceHolderActor
import dev.yuriel.mahjan.actor.SideTilePlaceHolderActor
import dev.yuriel.mahjan.actor.TileActor
import dev.yuriel.mahjan.enums.TileSide
import dev.yuriel.mahjan.texture.TileMgr

/**
 * Created by yuriel on 8/6/16.
 */
class LeftSideGroup : TileGroup() {
    override val tileList: Array<LeftTilePlaceHolderActor>

    init {
        TileMgr.load()
        tileList = Array(13) { i ->
            val actor = LeftTilePlaceHolderActor()
            actor.position = i
            actor.addListener(object: InputListener(){
                override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {

                    return true
                }
            })
            addActor(actor)
            actor
        }
        setPosition(20F * Dev.UX, -30F * Dev.UY)
    }
}