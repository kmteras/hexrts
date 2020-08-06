package hexrts.core.definition

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion

enum class TilemapReference(val x: Int, val y: Int, val width: Int = 120, val height: Int = 140) {
    Grass(610, 142),
    Dirt(732, 710),
    Forest(488, 994),
    Sand(244, 426),
    Stone(0, 1704),
    Lumber(610, 852),
    StoreBorder(122, 568),
    Water(1342, 710),
    SandBorder(244, 1278),
    Tent(816, 434, 42, 46),
    Logs(862, 248, 36, 32);

    fun getTextureRegion(texture: Texture): TextureRegion {
        return TextureRegion(texture, x, y, width, height)
    }
}
