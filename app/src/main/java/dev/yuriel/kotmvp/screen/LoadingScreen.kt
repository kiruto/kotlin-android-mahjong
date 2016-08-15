/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 yuriel
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
 *
 */

package dev.yuriel.kotmvp.screen

import com.badlogic.gdx.Gdx.*
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import dev.yuriel.kotmvp.App
import dev.yuriel.kotmvp.Dev
import dev.yuriel.kotmvp.bases.BaseScreen
import dev.yuriel.mahjan.texture.TextureMgr
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.CountDownLatch
import javax.microedition.khronos.opengles.GL10
import kotlin.reflect.KClass

/**
 * Created by yuriel on 8/2/16.
 * @param next 次のスクリーン
 */
class LoadingScreen(val next: KClass<out BaseScreen>): BaseScreen() {

    private val batch: SpriteBatch by lazy { SpriteBatch() }
    private val font: BitmapFont by lazy { BitmapFont() }
    private val observable by lazy {
        val screen: BaseScreen = next.java.newInstance()
        Observable.just(screen)
                .observeOn(Schedulers.newThread())
                .map { screen ->
                    for (l in screen.preload()?: listOf()) {
                        l.load()
                    }
                    screen
                }
                .subscribeOn(AndroidSchedulers.mainThread())
    }

    private val subscriber by lazy {
        object: Subscriber<BaseScreen>() {
            override fun onNext(screen: BaseScreen) {
                Dev.game?.screen = screen
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    override fun show() {
        observable.subscribe(subscriber)
    }

    override fun pause() {
        
    }

    override fun resize(width: Int, height: Int) {
        
    }

    override fun hide() {
        
    }

    var i = 0
    override fun render(delta: Float) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT)
        gl.glClearColor(0F, 0F, 0F, 0F)
        batch.begin()
        drawGrid()
        font.draw(batch, "now loading", 600 * Dev.UX + i ++, 30 * Dev.UY)
        batch.end()
    }

    override fun preload(): List<TextureMgr>? = null

    override fun resume() {
        
    }

    override fun dispose() {
        batch.dispose()
        font.dispose()
        subscriber.unsubscribe()
    }
}