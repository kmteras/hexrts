package hexrts.core.world.definition

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion

enum class TileType(val x: Int, val y: Int, val width: Int, val height: Int) {
    Grass(610, 142, 120, 140),
    Dirt(732, 710, 120, 140),
    Forest(488, 994, 120, 140),
    Sand(244, 426, 120, 140),
    Lumber(610, 852, 120, 140),
    StoreBorder(122, 568, 120, 140),
    SandBorder(244, 1278, 120, 140);

    fun getTextureRegion(texture: Texture): TextureRegion {
        return TextureRegion(texture, x, y, width, height)
    }
}
