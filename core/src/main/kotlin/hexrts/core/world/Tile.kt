package hexrts.core.world

import hexrts.core.world.definition.TileType

abstract class Tile(override val type: TileType) : BaseTile {
    companion object {
        const val SIZE = 32f
    }
}
