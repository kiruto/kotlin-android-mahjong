/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmvp.viewports

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.utils.viewport.FillViewport
import dev.yuriel.kotmvp.Dev

/**
 * Created by yuriel on 8/7/16.
 */
class FillViewportImpl(cam: Camera): FillViewport(Dev.getDefaultWidth(), Dev.getDefaultHeight(), cam)