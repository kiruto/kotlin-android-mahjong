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

package dev.yuriel.mahjan

import com.badlogic.gdx.graphics.FPSLogger
import dev.yuriel.kotmvp.Dev
import dev.yuriel.kotmvp.bases.BaseScreen
import dev.yuriel.mahjan.group.HandsGroup
import dev.yuriel.mahjan.interfaces.MainScreenPresenter
import dev.yuriel.mahjan.presenter.PlayPresenter
import dev.yuriel.mahjan.texture.NakiBtn
import dev.yuriel.mahjan.texture.TileMgr
import dev.yuriel.mahjan.texture.UITexture
import dev.yuriel.mahjan.views.MainGameRootViews

/**
 * Created by yuriel on 8/5/16.
 */
class MainGameScreen: BaseScreen(), MainScreenPresenter {

    private val fPSLogger = FPSLogger()
    val views = MainGameRootViews(this)
    val play = PlayPresenter(views)

    override fun preload() = listOf(TileMgr, UITexture)

    override fun show() {
        views.inject()
        //views.mockLayout4Test()
        play.addPlayer()
        //play.start()
        Dev.setViewport(Dev.stretchViewport)
    }

    override fun pause() {
        
    }

    override fun resize(width: Int, height: Int) {
        Dev.defaultViewport.update(width, height, true)
    }

    override fun hide() {
        
    }

    override fun render(delta: Float) {
        fPSLogger.log()
        views.clearScreen()
        drawGrid()
        views.render()
    }

    override fun resume() {
        
    }

    override fun dispose() {
        super.dispose()
        views.destroy()
    }

    override fun startRound() {
        play.start()
    }

    override fun getActionListener(): HandsGroup.Listener {
        return play
    }

    override fun getNakiBtnListener(): NakiBtn.Listener {
        return play
    }
}