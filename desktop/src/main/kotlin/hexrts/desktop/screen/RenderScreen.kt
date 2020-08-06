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
import com.badlogic.gdx.utils.viewport.ScreenViewport
import hexrts.core.util.ChunkPosition
import hexrts.core.world.Chunk
import hexrts.core.world.TileSelector
import hexrts.core.world.WorldGenerator
import hexrts.core.world.tile.Tile
import hexrts.desktop.input.ScreenGestureListener
import hexrts.desktop.input.ScreenInputProcessor
import hexrts.desktop.service.GuiBuildingService
import ktx.app.KtxScreen

class RenderScreen(
    private var width: Float,
    private var height: Float
) : KtxScreen {
    companion object {
        const val MOVE_SPEED = 400f
    }

    private val camera = OrthographicCamera(width, height)
    private val guiViewport = ScreenViewport()
    private val font = BitmapFont()

    init {
        font.region.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    private val batch = SpriteBatch().apply {
        color = Color.WHITE
    }

    private lateinit var tilemap: Texture
    private lateinit var objectTilemap: Texture
    private lateinit var tileSelector: TileSelector
    private val worldGenerator = WorldGenerator()
    private val world = worldGenerator.generateWorld()

    private val polygonBatch = PolygonSpriteBatch()
    private val gameUI = GameUI(guiViewport)
    private val buildingService = GuiBuildingService(world, gameUI)

    override fun show() {
        tileSelector = TileSelector(world, buildingService)
        val inputMultiplexer = InputMultiplexer()
        inputMultiplexer.addProcessor(gameUI.stage)
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

        gameUI.render(delta)
    }

    private fun renderChunk() {
        polygonBatch.projectionMatrix = camera.combined
        polygonBatch.begin()

        world.render(polygonBatch, tilemap, objectTilemap, tileSelector)

        polygonBatch.end()
    }

    override fun dispose() {
        font.dispose()
        batch.dispose()
        polygonBatch.dispose()
        tilemap.dispose()
        objectTilemap.dispose()
        gameUI.dispose()
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

        val localX = (camera.position.x / Chunk.CHUNK_SIZE / Tile.WIDTH).toInt()
        val localY = (camera.position.y / Chunk.CHUNK_SIZE / Tile.AVERAGE_HEIGHT).toInt()

        world.addChunkIfNotExists(ChunkPosition(localX, localY), worldGenerator.generateChunk(localX, localY))
        world.addChunkIfNotExists(ChunkPosition(localX + 1, localY), worldGenerator.generateChunk(localX + 1, localY))
        world.addChunkIfNotExists(ChunkPosition(localX - 1, localY), worldGenerator.generateChunk(localX - 1, localY))
        world.addChunkIfNotExists(ChunkPosition(localX, localY + 1), worldGenerator.generateChunk(localX, localY + 1))
        world.addChunkIfNotExists(ChunkPosition(localX, localY - 1), worldGenerator.generateChunk(localX, localY - 1))

        gameUI.updateText(delta, tileSelector.selectedTile.toString())
    }

    override fun resize(width: Int, height: Int) {
        guiViewport.update(width, height, true)

        this.width = width.toFloat()
        this.height = height.toFloat()
        camera.viewportWidth = this.width
        camera.viewportHeight = this.height
    }
}
