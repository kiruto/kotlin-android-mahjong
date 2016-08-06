package dev.yuriel.kotmvp.bases

import com.badlogic.gdx.Screen
import dev.yuriel.mahjan.texture.TextureMgr
import dev.yuriel.kotmvp.views.GridLayerViews

/**
 * Created by yuriel on 8/3/16.
 */
abstract class BaseScreen: Screen {
    private val grid: GridLayerViews = GridLayerViews()

    protected fun drawGrid() {
        grid.draw()
    }

    override fun dispose() {
        for (l in preload()?: return) {
            l.destroy()
        }
    }

    abstract fun preload(): List<TextureMgr>?
}