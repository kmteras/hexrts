package hexrts.desktop.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.input.GestureDetector
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextArea
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.badlogic.gdx.utils.viewport.ScreenViewport
import hexrts.core.util.PerlinNoiseGenerator
import hexrts.core.world.Chunk
import hexrts.core.world.TileSelector
import hexrts.core.world.World
import hexrts.core.world.tile.Tile
import hexrts.desktop.input.ScreenGestureListener
import hexrts.desktop.input.ScreenInputProcessor
import ktx.app.KtxScreen
import kotlin.math.roundToInt

class NoiseScreen(
    private var width: Float,
    private var height: Float
) : KtxScreen {
    private val camera = OrthographicCamera(width, height)
    private val guiViewport = ScreenViewport()

    private val batch = SpriteBatch()

    override fun show() {
    }

    override fun render(delta: Float) {
        val scale = 256
        val noiseGenerator = PerlinNoiseGenerator(1, scale + 1)
        val pixmap = Pixmap(128, 128, Pixmap.Format.RGBA8888)

        val showColor = Gdx.input.isKeyPressed(Input.Keys.SPACE)

        for(y in 0 until 128) {
            for (x in 0 until 128) {
                val noise = (noiseGenerator.perlin(x / 8f, y / 8f) + 1f) / 2f


                if (showColor) {
                    when {
                        noise < 0.4 -> {
                            pixmap.drawPixel(x, y, Color.rgba8888(1f, noise, noise, 1f))
                        }
                        noise < 0.45 -> {
                            pixmap.drawPixel(x, y, Color.rgba8888(noise, noise, 1f, 1f))
                        }
                        else -> {
                            pixmap.drawPixel(x, y, Color.rgba8888(noise, 1f, noise, 1f))
                        }
                    }
                } else {
                    pixmap.drawPixel(x, y, Color.rgba8888(noise, noise, noise, 1f))
                }
            }
        }

        val image = Texture(pixmap)

        batch.begin()

        batch.draw(image, 0f, 0f, 512f, 512f)

        batch.end()
    }

    override fun dispose() {
        batch.dispose()
    }

    override fun resize(width: Int, height: Int) {
        guiViewport.update(width, height)

        this.width = width.toFloat()
        this.height = height.toFloat()
        camera.viewportWidth = this.width
        camera.viewportHeight = this.height
    }
}
