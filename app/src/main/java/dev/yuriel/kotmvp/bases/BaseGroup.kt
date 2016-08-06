package dev.yuriel.kotmvp.bases

import com.badlogic.gdx.scenes.scene2d.Group

/**
 * Created by yuriel on 8/6/16.
 */
abstract class BaseGroup: Group() {
    abstract fun destroy()
}