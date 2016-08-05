package dev.yuriel.kotmvp.interfaces

import com.badlogic.gdx.Screen
import dev.yuriel.kotmvp.views.GridLayerViews

/**
 * Created by yuriel on 8/3/16.
 */
abstract class BaseScreen: Screen {
    private val grid: GridLayerViews = GridLayerViews()

    protected fun drawGrid() {
        grid.draw()
    }
}