package hexrts.desktop.tile

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonRegion
import com.badlogic.gdx.graphics.g2d.TextureRegion
import kotlin.math.sqrt

class TileGenerator {
    companion object {
        private val greenPixel = Pixmap(1, 1, Pixmap.Format.RGBA8888)

        init {
            greenPixel.setColor(Color.GREEN)
            greenPixel.fill()
        }
        val greenTexture = Texture(greenPixel);

        private val bluePixel = Pixmap(1, 1, Pixmap.Format.RGBA8888)

        init {
            bluePixel.setColor(Color.BLUE)
            bluePixel.fill()
        }

        val blueTexture = Texture(bluePixel);

        const val size = 32f

        fun getRenderTile(texture: Texture): RenderTile {
            val region = PolygonRegion(
                TextureRegion(texture),
                floatArrayOf(
                    0f, 0f,
                    0f, size,
                    size * sqrt(3f) / 2, size / 2,
                    size * sqrt(3f) / 2, -size / 2,
                    0f, -size,
                    -size * sqrt(3f) / 2, -size / 2,
                    -size * sqrt(3f) / 2, size / 2
                ),
                shortArrayOf(
                    0, 1, 2,
                    0, 2, 3,
                    0, 3, 4,
                    0, 4, 5,
                    0, 5, 6,
                    0, 6, 1
                )
            )

            return RenderTile(region)
        }
    }
}
