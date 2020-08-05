package hexrts.desktop.screen

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
import hexrts.core.world.TileSelector
import hexrts.desktop.input.ScreenGestureListener
import hexrts.desktop.input.ScreenInputProcessor
import ktx.app.KtxScreen
import kotlin.math.roundToInt

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
    private lateinit var objectTilemap: Texture
    private val chunk = Chunk.getPredefinedChunk()
    private lateinit var tileSelector: TileSelector

    private val polygonBatch = PolygonSpriteBatch()

    override fun show() {
        tileSelector = TileSelector(chunk)
        val inputMultiplexer = InputMultiplexer()
        inputMultiplexer.addProcessor(GestureDetector(ScreenGestureListener(camera, tileSelector)))
        inputMultiplexer.addProcessor(ScreenInputProcessor(camera, tileSelector))
        Gdx.input.inputProcessor = inputMultiplexer

        tilemap = Texture(Gdx.files.internal("tiles_sheet.png"))
        objectTilemap = Texture(Gdx.files.internal("objects_sheet.png"))
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

        chunk.render(polygonBatch, tilemap, objectTilemap, tileSelector)

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
