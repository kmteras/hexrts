package hexrts.desktop.render

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxScreen

class RenderScreen : KtxScreen {
    private val font = BitmapFont()
    private val batch = SpriteBatch().apply {
        color = Color.WHITE
    }

    override fun render(delta: Float) {
        batch.begin()

        font.draw(batch, "Hello World", 100f, 100f)

        batch.end()
    }

    override fun dispose() {
        font.dispose()
        batch.dispose()
    }
}
