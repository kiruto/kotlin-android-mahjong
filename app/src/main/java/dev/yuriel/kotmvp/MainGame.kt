package dev.yuriel.kotmvp

import com.badlogic.gdx.Game
import dev.yuriel.kotmvp.bases.BaseGame
import dev.yuriel.kotmvp.screen.LoadingScreen
import dev.yuriel.kotmvp.screen.TitleScreen
import dev.yuriel.mahjan.MainGameScreen

/**
 * Created by yuriel on 8/2/16.
 */
class MainGame: BaseGame() {
    override fun create() {
        super.create()
        val screen = LoadingScreen(MainGameScreen::class)
        setScreen(screen)
    }
}