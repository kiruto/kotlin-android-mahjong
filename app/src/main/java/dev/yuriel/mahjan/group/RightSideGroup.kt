package dev.yuriel.mahjan.group

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import dev.yuriel.kotmvp.Dev
import dev.yuriel.mahjan.actor.RightTilePlaceHolderActor
import dev.yuriel.mahjan.actor.SideTilePlaceHolderActor
import dev.yuriel.mahjan.enums.TileSide
import dev.yuriel.mahjan.texture.TileMgr

/**
 * Created by yuriel on 8/7/16.
 */
class RightSideGroup: TileGroup() {
    override val tileList: Array<RightTilePlaceHolderActor>

    init {
        TileMgr.load()
        tileList = Array(13) { i ->
            val actor = RightTilePlaceHolderActor()
            actor.position = i
            actor.addListener(object: InputListener(){
                override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {

                    return true
                }
            })
            addActor(actor)
            actor
        }
        setPosition(Dev.MAX_X * Dev.UX - 50 * Dev.UX, 0F)
    }
}