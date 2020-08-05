package hexrts.desktop.render

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.input.GestureDetector
import hexrts.core.world.Chunk
import hexrts.core.world.Tile
import hexrts.desktop.input.ScreenGestureListener
import hexrts.desktop.input.ScreenInputProcessor
import ktx.app.KtxScreen
import kotlin.math.roundToInt
import kotlin.math.sqrt

class RenderScreen(
    private var width: Float,
    private var height: Float
) : KtxScreen {
    companion object {
        const val MOVE_SPEED = 400f
    }

    private val camera = OrthographicCamera(width, height)
    private val font = BitmapFont()
    private val batch = SpriteBatch().apply {
        color = Color.WHITE
    }

    private lateinit var tilemap: Texture

    private val polygonBatch = PolygonSpriteBatch()

    override fun show() {
        val inputMultiplexer = InputMultiplexer()
        inputMultiplexer.addProcessor(GestureDetector(ScreenGestureListener(camera)))
        inputMultiplexer.addProcessor(ScreenInputProcessor(camera))
        Gdx.input.inputProcessor = inputMultiplexer

        tilemap = Texture(Gdx.files.internal("tiles_sheet.png"))
    }

    override fun render(delta: Float) {
        update(delta)
        camera.update()

        renderChunk()

        batch.begin()
        font.draw(batch, "Hello World ${(1 / delta).roundToInt()}fps", 10f, 20f)
        batch.end()
    }

    private fun renderChunk() {
        polygonBatch.projectionMatrix = camera.combined
        polygonBatch.begin()
        val chunk = Chunk.getPredefinedChunk()

        chunk.tiles.forEachIndexed { y, xTiles ->
            xTiles.forEachIndexed { x, tile ->
                val renderOffsetX = if (y % 2 == 0) 0f else Tile.SIZE * sqrt(3f) / 2

                val textureRegion = tile.getTextureRegion(tilemap)

                polygonBatch.draw(textureRegion,
                    x * Tile.SIZE * sqrt(3f) + renderOffsetX,
                    y * (Tile.SIZE * 1.5f - 1f),
                    Tile.SIZE * sqrt(3f),
                    Tile.SIZE * 2
                )
            }
        }
        polygonBatch.end()
    }

    override fun dispose() {
        font.dispose()
        batch.dispose()
        polygonBatch.dispose()
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
