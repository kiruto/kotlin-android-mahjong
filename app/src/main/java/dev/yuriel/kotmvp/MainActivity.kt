package dev.yuriel.kotmvp

import android.os.Bundle
import android.os.PersistableBundle
import android.os.PowerManager
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import dev.yuriel.kotmvp.bases.BaseActivity

/**
 * Created by yuriel on 8/1/16.
 */
class MainActivity: AndroidApplication() {

    var mWakelock: PowerManager.WakeLock? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pm = getSystemService(POWER_SERVICE) as PowerManager
        mWakelock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP or PowerManager.SCREEN_DIM_WAKE_LOCK, "target")
        mWakelock?.acquire()

        val config = AndroidApplicationConfiguration()
        initialize(MainGame(), config)
    }

    override fun onPause() {
        super.onPause()
        try {
            mWakelock?.release()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}