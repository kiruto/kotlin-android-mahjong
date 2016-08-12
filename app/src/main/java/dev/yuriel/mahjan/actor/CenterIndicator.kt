package dev.yuriel.mahjan.actor

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import dev.yuriel.kotmvp.Dev
import dev.yuriel.kotmvp.FURO_TILE_WIDTH
import dev.yuriel.kotmvp.bases.BaseActor
import dev.yuriel.mahjan.texture.UITexture

/**
 * Created by yuriel on 8/11/16.
 */
class CenterIndicator: BaseActor() {

    private val texture: TextureRegion?
    private val size = Pair(FURO_TILE_WIDTH * 6F, FURO_TILE_WIDTH * 2F)

    init {
        UITexture.load()
        texture = UITexture[38]
        setSize(size.first * Dev.U, size.second * Dev.U)
    }

    override fun destroy() {

    }

    override fun onDraw(batch: Batch?, parentAlpha: Float) {
        batch?.draw(texture, x, y, size.first * Dev.U, size.second * Dev.U)
    }
}