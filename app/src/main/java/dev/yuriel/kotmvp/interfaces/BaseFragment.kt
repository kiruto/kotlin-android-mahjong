package dev.yuriel.kotmvp.interfaces

import android.os.Bundle
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.backends.android.AndroidFragmentApplication

/**
 * Created by yuriel on 8/2/16.
 */
class BaseFragment: AndroidFragmentApplication() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}