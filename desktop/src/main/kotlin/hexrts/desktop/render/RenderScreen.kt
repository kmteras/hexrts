package hexrts.desktop.render

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.input.GestureDetector
import hexrts.desktop.input.ScreenGestureListener
import hexrts.desktop.input.ScreenInputProcessor
import hexrts.desktop.tile.TileGenerator
import ktx.app.KtxScreen
import kotlin.math.sqrt

class RenderScreen(
    var width: Float,
    var height: Float
) : KtxScreen {
    companion object {
        const val MOVE_SPEED = 400f
    }

    private val camera = OrthographicCamera(width, height)
    private val font = BitmapFont()
    private val batch = SpriteBatch().apply {
        color = Color.WHITE
    }

    private val polygonBatch = PolygonSpriteBatch()

    override fun show() {
        val inputMultiplexer = InputMultiplexer()
        inputMultiplexer.addProcessor(GestureDetector(ScreenGestureListener(camera)))
        inputMultiplexer.addProcessor(ScreenInputProcessor(camera))
        Gdx.input.inputProcessor = inputMultiplexer
    }

    override fun render(delta: Float) {
        update(delta)

        camera.update()
        polygonBatch.projectionMatrix = camera.combined
        polygonBatch.begin()
        for (x in 0..8) {
            for (y in 0..8) {
                val texture = if (y % 2 == 0) TileGenerator.blueTexture else TileGenerator.greenTexture
                val hex = TileGenerator.getRenderTile(texture)

                val renderOffsetX = if (y % 2 == 0) 0f else TileGenerator.size * sqrt(3f) / 2

                hex.setPosition(
                    x * TileGenerator.size * sqrt(3f) + renderOffsetX,
                    y * TileGenerator.size * 1.5f
                )
                hex.draw(polygonBatch)
            }
        }
        polygonBatch.end()

        batch.begin()

        font.draw(batch, "Hello World", 100f, 100f)

        batch.end()
    }

    override fun dispose() {
        font.dispose()
        batch.dispose()
    }

    private fun update(delta: Float) {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.translate(MOVE_SPEED * delta, 0f)
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.translate(-MOVE_SPEED * delta, 0f)
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.translate(0f, MOVE_SPEED * delta)
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            camera.translate(0f, -MOVE_SPEED * delta)
        }
    }

    override fun resize(width: Int, height: Int) {
        this.width = width.toFloat()
        this.height = height.toFloat()
        camera.viewportWidth = this.width
        camera.viewportHeight = this.height
    }
}
