package dev.yuriel.kotmvp.screen

import com.badlogic.gdx.Gdx.*
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import dev.yuriel.kotmvp.App
import dev.yuriel.kotmvp.Dev
import dev.yuriel.kotmvp.interfaces.BaseScreen
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.microedition.khronos.opengles.GL10
import kotlin.reflect.KClass

/**
 * Created by yuriel on 8/2/16.
 * @param next 次のスクリーン
 * @param obj 次を読み込むから必要なパラメータ
 * @param run ロードメソード
 */
class LoadingScreen<T, K>(val next: KClass<out Screen>, val obj: K, val run: (K) -> T): BaseScreen() {

    private val batch: SpriteBatch by lazy { SpriteBatch() }
    private val font: BitmapFont by lazy { BitmapFont() }
    private val observable by lazy {
        Observable.just(obj)
                .observeOn(Schedulers.newThread())
                .map { run(obj) }
                .subscribeOn(AndroidSchedulers.mainThread())
    }

    private val subscriber by lazy {
        object: Subscriber<T>() {
            override fun onNext(t: T) {
                app.postRunnable {
                    val screen: Screen
                    try {
                        screen = next.java.getConstructor(next.java).newInstance(i)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        screen = next.java.newInstance()
                    }
                    Dev.game?.screen = screen
                }
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
        drawGrid()
        batch.begin()
        font.draw(batch, "now loading", 600 * Dev.UX + i ++, 30 * Dev.UY)
        batch.end()
    }

    override fun resume() {
        
    }

    override fun dispose() {
        batch.dispose()
        font.dispose()
        subscriber.unsubscribe()
    }
}