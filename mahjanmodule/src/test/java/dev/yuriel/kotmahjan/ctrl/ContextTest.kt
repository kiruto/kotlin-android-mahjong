/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 yuriel<yuriel3183@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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