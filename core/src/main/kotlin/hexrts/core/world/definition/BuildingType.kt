package hexrts.core.world.definition

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion

enum class BuildingType(val x: Int, val y: Int, val width: Int, val height: Int) {
    Base(816, 434, 42, 46);

    fun getTextureRegion(texture: Texture): TextureRegion {
        return TextureRegion(texture, x, y, width, height)
    }
}
