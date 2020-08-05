package hexrts.desktop.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.Viewport
import kotlin.math.roundToInt

class GameUI(private val viewport: Viewport) : Disposable {
    private val skin = Skin(Gdx.files.internal("data/uiskin.json"))
    private val stage = Stage(viewport)
    private val fpsLabel = Label("x fps", skin)
    private val selectedTileText = TextField("Selected tile: x", skin)

    init {
        selectedTileText.style.background.topHeight = 10f
        selectedTileText.setPosition(2f, 2f)

        stage.addActor(fpsLabel)
        stage.addActor(selectedTileText)
    }

    fun updateText(delta: Float, selectedTile: String) {
        fpsLabel.setText("${(1 / delta).roundToInt()} fps")
        selectedTileText.text = "Selected tile: $selectedTile"
    }

    fun render(delta: Float) {
        fpsLabel.setPosition(5f, viewport.topGutterY - 25f)
        selectedTileText.width = viewport.worldWidth - 4f

        stage.draw()
    }

    override fun dispose() {
        skin.dispose()
        stage.dispose()
    }
}
