/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.mahjan.group

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import dev.yuriel.kotmvp.Dev
import dev.yuriel.kotmvp.FURO_TILE_WIDTH
import dev.yuriel.kotmvp.bases.BaseGroup
import dev.yuriel.mahjan.actor.CenterFont
import dev.yuriel.mahjan.actor.CenterIndicator

/**
 * Created by yuriel on 8/13/16.
 */
class CenterTableGroup(private val callback: () -> Unit): BaseGroup() {

    val bg: CenterIndicator = CenterIndicator()
    val idText: CenterFont = CenterFont()

    init {
        addActor(bg)
        addActor(idText)

        idText.addAction(Actions.sequence(
                Actions.parallel(
                        Actions.scaleTo(0.8F, 0.8F, 0.22F),
                        Actions.fadeIn(0.2F)
                ),
                Actions.scaleTo(0.8F, 0.8F, 2F),
                Actions.parallel(
                        Actions.scaleTo(0.2F, 0.2F, 0.4F),
                        Actions.moveBy(- FURO_TILE_WIDTH * 1.5F * Dev.U, FURO_TILE_WIDTH * 0.5F * Dev.U, 0.4F)
                ),
                Actions.run() {
                    callback()
                }
        ))
        idText.setPosition(- FURO_TILE_WIDTH * 2.25F * Dev.U, FURO_TILE_WIDTH * 2.75F * Dev.U)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
    }

    override fun destroy() {

    }
}