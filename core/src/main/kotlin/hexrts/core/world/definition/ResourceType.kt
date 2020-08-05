package hexrts.core.world.definition

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion

enum class ResourceType(val x: Int, val y: Int, val width: Int, val height: Int) {
    Wood(610, 142, 120, 140),
    Stone(732, 710, 120, 140),
    Copper(488, 994, 120, 140),
    Coal(244, 426, 120, 140),
    Iron(244, 426, 120, 140);

    fun getTextureRegion(texture: Texture): TextureRegion {
        return TextureRegion(texture, x, y, width, height)
    }
}
