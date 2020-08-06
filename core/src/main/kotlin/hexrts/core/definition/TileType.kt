package hexrts.core.definition

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion

enum class TileType(private val tilemapReference: TilemapReference) {
    Grass(TilemapReference.Grass),
    Dirt(TilemapReference.Dirt),
    Forest(TilemapReference.Forest),
    Sand(TilemapReference.Sand),
    Stone(TilemapReference.Stone),
    Lumber(TilemapReference.Lumber),
    Water(TilemapReference.Water);

    fun getTextureRegion(texture: Texture): TextureRegion {
        return tilemapReference.getTextureRegion(texture)
    }
}
