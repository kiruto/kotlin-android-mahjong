/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.mahjan.group

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmvp.bases.BaseGroup
import dev.yuriel.mahjan.actor.TileActor
import dev.yuriel.mahjan.texture.TileMgr

/**
 * Created by yuriel on 8/6/16.
 */
abstract class TileGroup<out ACTOR: TileActor>: BaseGroup() {
    val tileList: Array<out TileActor>
    abstract fun getOrigin(): Pair<Float, Float>
    protected abstract fun factory(): ACTOR

    init {
        TileMgr.load()
        tileList = Array<TileActor>(13) { i ->
            val actor = factory()
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