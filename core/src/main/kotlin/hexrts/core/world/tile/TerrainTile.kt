package hexrts.core.world.tile

import hexrts.core.world.building.BaseBuilding
import hexrts.core.world.definition.TileType

class TerrainTile(
    type: TileType,
    val building: BaseBuilding? = null
) : Tile(type) {

    override fun update(delta: Float) {

    }

    override fun toString(): String {
        return "Tile(type=$type, building=$building)"
    }
}
