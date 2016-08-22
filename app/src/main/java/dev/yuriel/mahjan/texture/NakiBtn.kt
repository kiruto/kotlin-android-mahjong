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

package dev.yuriel.mahjan.texture

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import dev.yuriel.kotmvp.bases.BaseActor

/**
 * Created by yuriel on 8/18/16.
 */
class NakiBtn(val naki: Naki): BaseActor() {

    var listener: Listener? = null

    private val texture by lazy {
        when (naki) {
            Naki.CHI -> Texture("btn_chi.gif")
            Naki.PON -> Texture("btn_pon.gif")
            Naki.KAN -> Texture("btn_kan.gif")
            Naki.RON -> Texture("btn_ron.gif")
            Naki.TSUMO -> Texture("btn_tsumo.gif")
            Naki.YES -> Texture("btn_naki.gif")
            Naki.NO -> Texture("btn_no_naki.gif")
        }
    }

    init {
        val l = object: ClickListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                if (null != listener) {
                    listener!!.onNakiBtnTouchDown(this@NakiBtn)
                }
                return super.touchDown(event, x, y, pointer, button)
            }

            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                super.touchUp(event, x, y, pointer, button)
                if (null != listener) {
                    listener!!.onNakiBtnTouchUp(this@NakiBtn)
                }
            }
        }
        addListener(l)
    }

    fun show(delay: Number = 0F) {
        addAction(Actions.sequence(
                Actions.moveBy(0F, 0F, delay.toFloat()),
                Actions.moveBy(-width, 0F, 0.1F)
        ))
    }

    fun hide(delay: Number = 0F) {
        addAction(Actions.sequence(
                Actions.moveBy(0F, 0F, delay.toFloat()),
                Actions.moveBy(width, 0F, 0.1F)
        ))
    }

    override fun destroy() {
        texture.dispose()
    }

    override fun onDraw(batch: Batch?, parentAlpha: Float) {
        batch?.draw(texture, x, y, width, height)
    }

    interface Listener {
        fun onNakiBtnTouchDown(btn: NakiBtn)
        fun onNakiBtnTouchUp(btn: NakiBtn)
    }
}