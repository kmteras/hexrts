package hexrts.core.world

import hexrts.core.world.definition.TileType

class TerrainTile(
    type: TileType,
    val building: BaseBuilding? = null
) : Tile(type) {

    override fun update(delta: Float) {

    }
}
