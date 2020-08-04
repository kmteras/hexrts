package hexrts.desktop.render

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import hexrts.desktop.tile.TileGenerator
import ktx.app.KtxScreen
import kotlin.math.sqrt

class RenderScreen : KtxScreen {
    private val font = BitmapFont()
    private val batch = SpriteBatch().apply {
        color = Color.WHITE
    }

    private val polygonBatch = PolygonSpriteBatch()

    override fun render(delta: Float) {
        polygonBatch.begin()
        for (x in 0..10) {
            for (y in 0..10) {
                val texture = if (y % 2 == 0) TileGenerator.blueTexture else TileGenerator.greenTexture
                val hex = TileGenerator.getRenderTile(texture)

                val renderOffsetX = if (y % 2 == 0) 0f else TileGenerator.size * sqrt(3f) / 2

                hex.setPosition(
                    x * TileGenerator.size * sqrt(3f) + renderOffsetX,
                    y * TileGenerator.size * 1.5f)
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
}
