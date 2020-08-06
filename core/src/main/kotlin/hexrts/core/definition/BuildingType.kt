package hexrts.core.definition

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import hexrts.core.world.tile.BaseTile

enum class BuildingType(
    private val tilemapReference: TilemapReference,
    val cost: Map<ResourceType, Int> = mapOf(),
    private val allowedTiles: Set<TileType> = setOf()
) {
    Base(TilemapReference.Tent),
    Logging(TilemapReference.Logs, mapOf(Pair(ResourceType.Wood, 10)), setOf(TileType.Forest));

    fun getTextureRegion(texture: Texture): TextureRegion {
        return tilemapReference.getTextureRegion(texture)
    }

    fun canBuildOn(tile: BaseTile): Boolean {
        return allowedTiles.contains(tile.type)
    }
}
