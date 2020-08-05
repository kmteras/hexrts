package hexrts.core.world.tile

import hexrts.core.world.definition.TileType
import hexrts.core.world.tile.BaseTile

abstract class Tile(override val type: TileType) : BaseTile {
    companion object {
        const val SIZE = 32f
    }
}
