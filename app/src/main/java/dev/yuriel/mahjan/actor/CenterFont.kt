package dev.yuriel.mahjan.actor

import com.badlogic.gdx.graphics.g2d.Batch
import dev.yuriel.kotmvp.bases.BaseActor
import dev.yuriel.mahjan.texture.NormalFontMgr

/**
 * Created by yuriel on 8/13/16.
 */
class CenterFont: BaseActor() {
    private var font: NormalFontMgr?
    var text: String = ""
        set(value) {
            field = value
            font?.painter?.text(field)
        }

    init {
        font = NormalFontMgr()
        font?.load("kyoku.fnt", "kyoku.png")
                ?.color(0F, 0F, 0F, 1F)
                ?.scale(0.5F, 0.5F)
                ?.scale { i -> Pair((i.toFloat() % 100F) / 10F, (i.toFloat() % 100F) / 10F) }
                ?.origin(x, y)
        width = 100F
        height = 100F
    }

    override fun destroy() {
        font = null
    }

    override fun onDraw(batch: Batch?, parentAlpha: Float) {
        if (null != batch) font?.draw(batch) else font?.draw()
    }
}