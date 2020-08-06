package hexrts.desktop.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.badlogic.gdx.utils.viewport.Viewport
import hexrts.core.world.TileSelector
import hexrts.core.world.World
import hexrts.core.world.tile.Tile

class WorldHud(
    viewport: Viewport,
    private val camera: Camera,
    private val world: World,
    private val tileSelector: TileSelector
) : Disposable {
    private val stage = Stage(viewport)
    private val skin = Skin(Gdx.files.internal("data/uiskin.json"))
    private val selectedTileText = TextField("", skin)
    private val glyphLayout = GlyphLayout()

    init {
        selectedTileText.isVisible = false
        selectedTileText.setOrigin(Align.right)
        stage.addActor(selectedTileText)
    }

    fun render() {
        val selectedTilePosition = tileSelector.selectedTilePosition

        if (selectedTilePosition == null) {
            selectedTileText.isVisible = false
        } else {
            val screenCoords = camera.project(selectedTilePosition.getWorldVector().add(Tile.WIDTH, Tile.HEIGHT, 0f))

            val tile = world.getTile(selectedTilePosition)

            selectedTileText.text = tile?.type.toString()
            selectedTileText.isVisible = true
            selectedTileText.width = getTextWidth(selectedTileText.text) + 8
            selectedTileText.setPosition(screenCoords.x - selectedTileText.width / 2, screenCoords.y)
        }

        stage.draw()
    }

    override fun dispose() {
        skin.dispose()
        stage.dispose()
    }

    private fun getTextWidth(text: String): Float {
        glyphLayout.setText(skin.getFont("default-font"), text)
        return glyphLayout.width
    }
}
