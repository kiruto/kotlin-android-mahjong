package dev.yuriel.kotmvp.viewports

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.utils.viewport.FitViewport
import dev.yuriel.kotmvp.Dev

/**
 * Created by yuriel on 8/7/16.
 */
class FitViewportImpl(cam: Camera): FitViewport(Dev.getDefaultWidth(), Dev.getDefaultHeight(), cam)