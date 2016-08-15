/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.mahjan.group

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmvp.Dev
import dev.yuriel.mahjan.actor.TilePlaceHolderActor
import rx.Observable

/**
 * Created by yuriel on 8/5/16.
 */
class HandsGroup: TileGroup<TilePlaceHolderActor>() {

    private val actionListener = mutableListOf<Listener>()

    override fun getOrigin(): Pair<Float, Float> = Pair(50F * Dev.UX, 0F)
    override fun factory(position: Int, isTsumo: Boolean): TilePlaceHolderActor {
        val actor = TilePlaceHolderActor(position, isTsumo)
        val listener = object: ClickListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                //println("touch down: ${actor.tile?.hai.toString()}")
                for (l in actionListener) {
                    l.onTouchDown(actor.tile?.hai?: break)
                }
                return super.touchDown(event, x, y, pointer, button)
            }

            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                //println("touch up: ${actor.tile?.hai.toString()}")
                super.touchUp(event, x, y, pointer, button)
                for (l in actionListener) {
                    l.onTouchUp(actor.tile?.hai?: break)
                }
            }
        }
        actor.addListener(listener)
        return actor
    }

    fun addOnActionListener(l: Listener) {
        actionListener.add(l)
    }

    interface Listener {
        fun onTouchDown(hai: Hai)
        fun onTouchUp(hai: Hai)
    }
}
