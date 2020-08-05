package hexrts.core.world

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion

interface BaseTile {
    val type: TileType

    fun getTextureRegion(texture: Texture): TextureRegion {
        return TextureRegion(texture, type.x, type.y, type.width, type.height)
    }
}
