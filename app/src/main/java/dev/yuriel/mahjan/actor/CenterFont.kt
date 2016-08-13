package dev.yuriel.mahjan.actor

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import dev.yuriel.kotmvp.bases.BaseActor
import dev.yuriel.mahjan.animator.GradientUtil
import dev.yuriel.mahjan.texture.NormalFontBlock

/**
 * Created by yuriel on 8/13/16.
 */
class CenterFont: BaseActor() {
    var font: NormalFontBlock?
        private set

    var text: String = ""
        set(value) {
            field = value
            font?.painter?.text(field)
            update()

        }

    override fun setPosition(x: Float, y: Float) {
        super.setPosition(x, y)
        font?.painter?.origin(x, y)
    }

    init {
        font = NormalFontBlock()

        //val animator = GradientUtil.linearAnimator(200, 110, 1F, 0.2F)

        font!!.load("kyoku.fnt", "kyoku.png")
                .color { i -> Color(255F, 255F, 255F, color.a) }
                .scale { i -> Pair(scaleX, scaleY) }
                //.scale(animator.get())
                //.scale { i -> Pair((i.toFloat() % 100F) / 10F, (i.toFloat() % 100F) / 10F) }
                .origin { i -> font!!.getCenterOriginToText(x, y, width, height) }
        update()
    }

    fun update() {
        width = font!!.layout.width
        height = font!!.layout.height
    }

    override fun destroy() {
        font = null
    }

    override fun onDraw(batch: Batch?, parentAlpha: Float) {
        if (null != batch) font?.draw(batch) else font?.draw()
    }
}