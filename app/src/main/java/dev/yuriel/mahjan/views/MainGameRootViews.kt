/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 yuriel
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
 *
 */

package dev.yuriel.mahjan.views

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmvp.*
import dev.yuriel.kotmvp.views.Views
import dev.yuriel.mahjan.MockData4Test
import dev.yuriel.mahjan.actor.LastFont
import dev.yuriel.mahjan.group.*
import dev.yuriel.mahjan.interfaces.MainScreenPresenter
import dev.yuriel.mahjan.interfaces.PlayViewsInterface
import dev.yuriel.mahjan.stage.ViewStage
import dev.yuriel.mahjan.texture.Naki
import dev.yuriel.mahjan.texture.NakiBtn

/**
 * Created by yuriel on 8/5/16.
 */
class MainGameRootViews(val presenter: MainScreenPresenter): Views(), PlayViewsInterface {
    internal val background = Texture("table.jpg")
    internal val handGroup = HandsGroup()
    internal val leftGroup = LeftSideGroup()
    internal val rightGroup = RightSideGroup()
    internal val oppoGroup = OpposideGroup()

    internal val riverBottomGroup = RiverGroup()
    internal val riverLeftGroup = RiverGroup()
    internal val riverRightGroup = RiverGroup()
    internal val riverOppoGroup = RiverGroup()

    internal val btnNaki = NakiBtn(Naki.YES)
    internal val btnNoNaki = NakiBtn(Naki.NO)
    internal val btnChi = NakiBtn(Naki.CHI)
    internal val btnPon = NakiBtn(Naki.PON)
    internal val btnKan = NakiBtn(Naki.KAN)
    internal val btnRon = NakiBtn(Naki.RON)
    internal val btnTsumo = NakiBtn(Naki.TSUMO)

    internal val centerTableGroup = CenterTableGroup()
    internal val lastFont = LastFont()

    val rootStage = ViewStage()

    override fun updateKawaFor(position: Int, haiList: List<Hai>) {
        val river: RiverGroup = when (position) {
            0 -> riverBottomGroup
            1 -> riverRightGroup
            2 -> riverOppoGroup
            3 -> riverLeftGroup
            else -> return
        }
        river.update(haiList)
    }

    override fun updateTehaiFor(position: Int, haiList: List<Hai>) {
        val hands: TileGroup<*> = when (position) {
            0 -> handGroup
            1 -> rightGroup
            2 -> oppoGroup
            3 -> leftGroup
            else -> return
        }
        hands.updateList(haiList)
    }

    override fun updateTsumoFor(position: Int, hai: Hai?) {
        val hands: TileGroup<*> = when (position) {
            0 -> handGroup
            1 -> rightGroup
            2 -> oppoGroup
            3 -> leftGroup
            else -> return
        }
        hands.updateTsumo(hai)
    }

    override fun updateHaisanLast(last: Int) {
        //Log.d("last", last.toString())
        lastFont.update(last)
    }

    override fun updateRoundText(roundText: String) {
        centerTableGroup.idText.text = roundText
        centerTableGroup.idText.animate { presenter.startRound() }
    }

    fun inject() {
        rootStage.addActor(handGroup)
        rootStage.addActor(leftGroup)
        rootStage.addActor(rightGroup)
        rootStage.addActor(oppoGroup)

        rootStage.addActor(riverBottomGroup)
        rootStage.addActor(riverLeftGroup)
        rootStage.addActor(riverRightGroup)
        rootStage.addActor(riverOppoGroup)

        rootStage.addActor(centerTableGroup)
        rootStage.addActor(lastFont.actor)

        rootStage.addActor(btnNaki)
        rootStage.addActor(btnNoNaki)
        rootStage.addActor(btnChi)
        rootStage.addActor(btnPon)
        rootStage.addActor(btnKan)
        rootStage.addActor(btnRon)
        rootStage.addActor(btnTsumo)

        setPosition()

        setAction()

        initBtn()
    }

    private fun setAction() {
        handGroup.addOnActionListener(presenter.getActionListener())
        for (b in listOf(btnNaki, btnNoNaki, btnChi, btnPon, btnKan, btnRon, btnTsumo)) {
            b.listener = presenter.getNakiBtnListener()
        }
    }

    private fun initBtn() {
        hideBtn(btnNoNaki, btnKan, btnPon, btnChi, btnRon, btnTsumo)
    }

    private fun hideBtn(vararg btn: NakiBtn) {
        var i = 0
        for (b in btn) {
            b.hide(0.1F * i++)
        }
    }

    private fun showBtn(vararg btn: NakiBtn) {
        var i = 0
        for (b in btn) {
            b.show(0.1F * i++)
        }
    }

    override fun showNaki() {
        btnNoNaki.hide()
        btnNaki.show(0.1F)
    }

    override fun hideNaki() {
        btnNaki.hide()
        btnNoNaki.show(0.1F)
    }

    fun mockLayout4Test() {
        handGroup.updateList(MockData4Test.instance.getTehaiList4Test())
        leftGroup.updateList(MockData4Test.instance.getTehaiList4Test())
        rightGroup.updateList(MockData4Test.instance.getTehaiList4Test())
        oppoGroup.updateList(MockData4Test.instance.getTehaiList4Test())

        riverBottomGroup.update(MockData4Test.instance2.getHaiList4Test(19))
        riverLeftGroup.update(MockData4Test.instance2.getHaiList4Test(19))
        riverRightGroup.update(MockData4Test.instance2.getHaiList4Test(19))
        riverOppoGroup.update(MockData4Test.instance2.getHaiList4Test(19))

        centerTableGroup.idText.text = "東一局"
    }

    fun clearScreen() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        Gdx.gl.glClearColor(0F, 0F, 0F, 0F)
    }

    fun render() {
        drawDesk()
        rootStage.active = true
        rootStage.render()
    }

    fun destroy() {
        rootStage.dispose()
    }

    private fun setPosition() {

        leftGroup.rotateBy(270F)
        rightGroup.rotateBy(90F)

        riverLeftGroup.rotateBy(270F)
        riverRightGroup.rotateBy(90F)
        riverOppoGroup.rotateBy(180F)

        layout()
    }

    private fun drawDesk() {
        rootStage.batch.begin()
        rootStage.batch.draw(background, 0F, 0F, Dev.getDefaultWidth(), Dev.getDefaultHeight())
        rootStage.batch.end()
    }
}