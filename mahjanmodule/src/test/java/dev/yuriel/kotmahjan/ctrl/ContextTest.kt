package dev.yuriel.kotmahjan.ctrl

import dev.yuriel.kotmahjan.ai.Console
import dev.yuriel.kotmahjan.ai.I
import dev.yuriel.kotmahjan.ctrl.impl.Round
import dev.yuriel.kotmahjan.ctrl.reactive.RoundController
import org.junit.Test

/**
 * Created by yuriel on 7/31/16.
 */
class ContextTest {
    @Test
    fun testController() {
        val rounder = Round()
        val ctrl = RoundController(rounder)
        val a = I("東方不敗")
        a.setListener(Console)
        val b = I("南夏奈")
        b.setListener(Console)
        val c = I("西山")
        c.setListener(Console)
        val d = I("堀北")
        d.setListener(Console)
        rounder.addPlayer(a, 0, a)
                .addPlayer(b, 1, b)
                .addPlayer(c, 2, c)
                .addPlayer(d, 3, d)
        ctrl.mainLoop()
    }
}