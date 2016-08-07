package dev.yuriel.kotmvp.viewports

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.utils.viewport.FillViewport
import dev.yuriel.kotmvp.Dev

/**
 * Created by yuriel on 8/7/16.
 */
class FillViewportImpl(cam: Camera): FillViewport(Dev.DEFAULT_WIDTH, Dev.DEFAULT_HEIGHT, cam)