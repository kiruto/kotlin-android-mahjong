package dev.yuriel.kotmvp

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
 * Created by yuriel on 8/1/16.
 */
class GdxGame: ApplicationAdapter() {
    var batch: SpriteBatch? = null
    var img: Texture? = null

    override fun create() {
        batch = SpriteBatch()
        img = Texture("ji7-66-90-s.png")
    }

    override fun render() {
        Gdx.gl.glClearColor(1F, 0F, 0F, 1F)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch?.begin()
        batch?.draw(img, 0F, 0F)
        batch?.end()
    }
}