package dev.yuriel.kotmvp

import android.os.Bundle
import android.os.PersistableBundle
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import dev.yuriel.kotmvp.interfaces.BaseActivity

/**
 * Created by yuriel on 8/1/16.
 */
class MainActivity: AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = AndroidApplicationConfiguration()
        initialize(MainGame(), config)
    }
}