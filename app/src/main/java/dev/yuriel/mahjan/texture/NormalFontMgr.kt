package dev.yuriel.mahjan.texture

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
 * Created by yuriel on 8/12/16.
 */
open class NormalFontMgr(private var batch: Batch? = null) {

    private var font: BitmapFont? = null
    var painter: Painter? = null
        private set

    fun load(fileName: String, imageName: String): Painter {
        font = BitmapFont(Gdx.files.internal(fileName), Gdx.files.internal(imageName), false)
        if (null == batch) {
            batch = SpriteBatch()
        }
        painter = Painter()
        return painter!!
    }

    fun draw() {
        painter?.draw()
    }

    fun draw(batch: Batch) {
        painter?.draw(batch)
    }

    inner class Painter internal constructor(){

        private var color: Color = Color(0F, 0F, 0F, 0F)
        private var x: Float = 0F
        private var y: Float = 0F
        private var scaleX: Float = 1F
        private var scaleY: Float = 1F
        private var text: String = ""
        private var frame: Int = 0
        private var colorGetter: ((Int) -> Color)? = null
        private var scaleGetter: ((Int) -> Pair<Float, Float>)? = null
        private var originGetter: ((Int) -> Pair<Float, Float>)? = null

        fun color(c: Color): Painter {
            color = c
            return this
        }

        fun color(r: Float, g: Float, b: Float, a: Float): Painter {
            color = Color(r, g, b, a)
            return this
        }

        fun color(f: (i: Int) -> Color): Painter {
            colorGetter = f
            return this
        }

        fun scale(x: Float, y: Float): Painter {
            scaleX = x
            scaleY = y
            return this
        }

        fun scale(f: (i: Int) -> Pair<Float, Float>): Painter {
            scaleGetter = f
            return this
        }

        fun text(t: String): Painter {
            text = t
            return this
        }

        fun origin(x: Float, y: Float): Painter {
            this.x = x
            this.y = y
            return this
        }

        fun origin(f: (i: Int) -> Pair<Float, Float>): Painter {
            originGetter = f
            return this
        }

        internal fun draw() {
            if (frame == Int.MAX_VALUE) frame = 0
            font?.color = colorGetter?.invoke(frame)?: color
            val scale: Pair<Float, Float>? = scaleGetter?.invoke(frame)
            font?.data?.scaleX = scale?.first?: scaleX
            font?.data?.scaleY = scale?.second?: scaleY
            val origin: Pair<Float, Float>? = originGetter?.invoke(frame)
            x = origin?.first?: x
            y = origin?.second?: y
            batch?.begin()
            font?.draw(batch, text, x, y)
            batch?.end()
            frame ++
        }

        internal fun draw(batch: Batch) {
            if (frame == Int.MAX_VALUE) frame = 0
            font?.color = colorGetter?.invoke(frame)?: color
            val scale: Pair<Float, Float>? = scaleGetter?.invoke(frame)
            font?.data?.scaleX = scale?.first?: scaleX
            font?.data?.scaleY = scale?.second?: scaleY
            val origin: Pair<Float, Float>? = originGetter?.invoke(frame)
            x = origin?.first?: x
            y = origin?.second?: y
            font?.draw(batch, text, x, y)
            frame ++
        }
    }
}