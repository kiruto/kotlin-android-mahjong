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