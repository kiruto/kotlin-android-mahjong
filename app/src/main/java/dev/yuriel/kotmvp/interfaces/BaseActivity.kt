package dev.yuriel.kotmvp.interfaces

import android.support.v7.app.AppCompatActivity
import dev.yuriel.kotmvp.Dev

/**
 * Created by yuriel on 8/2/16.
 */
open class BaseActivity: AppCompatActivity() {
    override fun onResume() {
        super.onResume()
        Dev.topActivity = this
    }
}