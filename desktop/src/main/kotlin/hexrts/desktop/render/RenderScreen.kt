package hexrts.desktop.render

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.input.GestureDetector
import com.badlogic.gdx.math.Vector2
import hexrts.desktop.tile.TileGenerator
import ktx.app.KtxScreen
import kotlin.math.sqrt

class RenderScreen : KtxScreen {
    private val font = BitmapFont()
    private val batch = SpriteBatch().apply {
        color = Color.WHITE
    }

    var x = 0f
    var y = 0f

    private val polygonBatch = PolygonSpriteBatch()

    override fun show() {
        val that = this
        Gdx.input.inputProcessor = GestureDetector(object : GestureDetector.GestureListener {
            override fun fling(velocityX: Float, velocityY: Float, button: Int): Boolean {
                return false
            }

            override fun zoom(initialDistance: Float, distance: Float): Boolean {
                return false
            }

            override fun pan(x: Float, y: Float, deltaX: Float, deltaY: Float): Boolean {
                that.x += deltaX
                that.y -= deltaY
                return false
            }

            override fun pinchStop() {
            }

            override fun tap(x: Float, y: Float, count: Int, button: Int): Boolean {
                return false
            }

            override fun panStop(x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return false
            }

            override fun longPress(x: Float, y: Float): Boolean {
                return false
            }

            override fun touchDown(x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return false
            }

            override fun pinch(
                initialPointer1: Vector2?,
                initialPointer2: Vector2?,
                pointer1: Vector2?,
                pointer2: Vector2?
            ): Boolean {
                return false
            }
        })
    }

    override fun render(delta: Float) {
        update(delta)

        polygonBatch.begin()
        for (x in 0..8) {
            for (y in 0..8) {
                val texture = if (y % 2 == 0) TileGenerator.blueTexture else TileGenerator.greenTexture
                val hex = TileGenerator.getRenderTile(texture)

                val renderOffsetX = if (y % 2 == 0) 0f else TileGenerator.size * sqrt(3f) / 2

                hex.setPosition(
                    x * TileGenerator.size * sqrt(3f) + renderOffsetX + this.x,
                    y * TileGenerator.size * 1.5f + this.y)
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
            x -= 100f * delta
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x += 100f * delta
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            y -= 100f * delta
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            y += 100f * delta
        }
    }
}
