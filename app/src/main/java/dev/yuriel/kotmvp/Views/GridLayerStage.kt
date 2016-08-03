package dev.yuriel.kotmvp.Views

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Label
import dev.yuriel.kotmvp.Dev

/**
 * Created by yuriel on 8/3/16.
 */
class GridLayerViews(val weight: Int = 4) {
    private val sr = ShapeRenderer()
    private val labels: MutableList<Label> = mutableListOf()

    fun draw() {
        Gdx.gl.glLineWidth(1F)
        sr.setAutoShapeType(true)
        sr.begin()
        sr.setColor(255F, 255F, 255F, 0.1F)
        val hw = 40 * Dev.UY / weight
        val ww = 40 * Dev.UX / weight
        for (w in 0..16 * weight) {
            val start = Vector2(w * ww, 0F)
            val end = Vector2(w * ww, 360F * Dev.UY)
            sr.line(start, end)
        }
        for (h in 0..9 * weight) {
            val start = Vector2(0F * Dev.UX, h * hw)
            val end = Vector2(640F * Dev.UX, h * hw)
            sr.line(start, end)
        }
        sr.end()
    }
}