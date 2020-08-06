package hexrts.desktop.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.Viewport
import hexrts.core.world.definition.TileType
import kotlin.math.roundToInt

class GameHud(private val viewport: Viewport) : Disposable {
    val stage = Stage(viewport)

    private val skin = Skin(Gdx.files.internal("data/uiskin.json"))
    private val fpsLabel = Label("x fps", skin)
    private val screenTable = Table()
    private val buttonsTable = Table(skin)
    private val buttonGroup = ButtonGroup<Button>()

    init {
        screenTable.setFillParent(true)

        buttonsTable.setBackground("default-pane")

        screenTable.add(buttonsTable).width(250f).height(150f).padRight(2f).padBottom(2f).expand().right().bottom()

        buttonsTable.padTop(10f).padLeft(10f).left().top()

        buttonGroup.add(addButton("Lumber yard"))
        buttonGroup.add(addButton("Dirt road"))

        buttonGroup.setMaxCheckCount(1)
        buttonGroup.setMinCheckCount(0)

        buttonGroup.setUncheckLast(true)
        buttonGroup.uncheckAll()

        stage.addActor(screenTable)
        stage.addActor(fpsLabel)
    }

    fun updateText(delta: Float) {
        fpsLabel.setText("${(1 / delta).roundToInt()} fps")
    }

    fun render() {
        fpsLabel.setPosition(5f, viewport.topGutterY - 25f)

        stage.draw()
    }

    override fun dispose() {
        skin.dispose()
        stage.dispose()
    }

    private fun addButton(text: String): Button {
        val button = TextButton(text, skin, "toggle")
        buttonsTable.add(button)
        return button
    }

    fun getSelectedTile(): TileType? {
        return when (buttonGroup.checkedIndex) {
            0 -> TileType.Lumber
            1 -> TileType.Dirt
            else -> null
        }
    }

    fun deselectTile() {
        buttonGroup.uncheckAll()
    }
}
