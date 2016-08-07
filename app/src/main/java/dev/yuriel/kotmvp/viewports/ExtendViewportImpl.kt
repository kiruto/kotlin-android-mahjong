package dev.yuriel.kotmvp.viewports

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.utils.viewport.ExtendViewport
import dev.yuriel.kotmvp.Dev

/**
 * Created by yuriel on 8/7/16.
 */
class ExtendViewportImpl(cam: Camera): ExtendViewport(Dev.getDefaultWidth(), Dev.getDefaultHeight(), cam)