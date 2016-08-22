/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 yuriel<yuriel3183@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.yuriel.mahjan.group

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import dev.yuriel.kotmvp.Dev
import dev.yuriel.kotmvp.FURO_TILE_WIDTH
import dev.yuriel.kotmvp.bases.BaseGroup
import dev.yuriel.mahjan.actor.CenterFont
import dev.yuriel.mahjan.actor.CenterIndicator
import dev.yuriel.mahjan.actor.LastFont

/**
 * Created by yuriel on 8/13/16.
 */
class CenterTableGroup: BaseGroup() {

    val bg: CenterIndicator = CenterIndicator()
    val idText: CenterFont = CenterFont()

    init {
        addActor(bg)
        addActor(idText)
        idText.setPosition(- FURO_TILE_WIDTH * 2.25F * Dev.U, FURO_TILE_WIDTH * 2.75F * Dev.U)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
    }

    override fun destroy() {

    }
}