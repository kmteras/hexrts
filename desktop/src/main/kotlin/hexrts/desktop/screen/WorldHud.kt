package hexrts.desktop.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextArea
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.Viewport
import hexrts.core.world.TileSelector
import hexrts.core.world.World
import hexrts.core.world.building.Building
import hexrts.core.world.tile.TerrainTile
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
    private val selectedTileInfo = TextArea("This is a test", skin)
    private val glyphLayout = GlyphLayout()

    init {
        selectedTileText.isVisible = false
        selectedTileText.setOrigin(Align.right)

        selectedTileText.style.background.topHeight = 8f
        selectedTileInfo.style.background.topHeight = 8f

        stage.addActor(selectedTileText)
        stage.addActor(selectedTileInfo)
    }

    fun render() {
        val selectedTilePosition = tileSelector.selectedTilePosition

        if (selectedTilePosition == null) {
            selectedTileText.isVisible = false
            selectedTileInfo.isVisible = false
        } else {
            val screenCoords = camera.project(selectedTilePosition.getWorldVector().add(Tile.WIDTH, Tile.HEIGHT, 0f))

            val tile = world.getTile(selectedTilePosition) as TerrainTile

            val infoText = getStorageText(tile)
            var infoBoxOffset = 0f

            if (infoText != null) {
                selectedTileInfo.text = infoText
                selectedTileInfo.isVisible = true

                selectedTileInfo.width = getTextWidth(infoText) + 8
                selectedTileInfo.height = getTextHeight(infoText) - 2
                selectedTileInfo.setPosition(screenCoords.x - selectedTileInfo.width / 2, screenCoords.y)
                infoBoxOffset = selectedTileInfo.height
            } else {
                selectedTileInfo.isVisible = false
            }

            selectedTileText.text = tile.type.toString()
            selectedTileText.isVisible = true
            selectedTileText.width = getTextWidth(selectedTileText.text) + 8
            selectedTileText.setPosition(
                screenCoords.x - selectedTileText.width / 2,
                screenCoords.y + infoBoxOffset
            )
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

    private fun getStorageText(tile: TerrainTile): String? {
        if (tile.building != null) {
            val building = tile.building as Building
            val storage = building.getStorageFeature()

            if (storage != null) {
                val storageMap = storage.getStorage()
                var storageText = ""

                storageMap.forEach { (resourceType, amount) ->
                    storageText += "$resourceType: $amount\n"
                }
                return storageText
            }
        }
        return null
    }

    private fun getTextHeight(text: String): Float {
        glyphLayout.setText(skin.getFont("default-font"), text)
        return glyphLayout.height
    }
}
