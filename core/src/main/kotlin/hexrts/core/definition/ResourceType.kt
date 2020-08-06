package hexrts.core.definition

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion

enum class ResourceType(val x: Int, val y: Int, val width: Int = 120, val height: Int = 140) {
    Wood(610, 142),
    Stone(732, 710),
    Copper(488, 994),
    Coal(244, 426),
    Iron(244, 426);

    fun getTextureRegion(texture: Texture): TextureRegion {
        return TextureRegion(texture, x, y, width, height)
    }
}
