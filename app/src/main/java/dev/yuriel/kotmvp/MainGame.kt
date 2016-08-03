package dev.yuriel.kotmvp

import com.badlogic.gdx.Game
import dev.yuriel.kotmvp.interfaces.BaseGame
import dev.yuriel.kotmvp.screen.LoadingScreen
import dev.yuriel.kotmvp.screen.TitleScreen

/**
 * Created by yuriel on 8/2/16.
 */
class MainGame: BaseGame() {
    override fun create() {
        val screen = LoadingScreen(TitleScreen::class, null) {
            Thread.sleep(5000)
        }
        setScreen(screen)
    }
}