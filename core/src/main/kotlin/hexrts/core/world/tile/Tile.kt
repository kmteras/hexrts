package hexrts.core.world.tile

import hexrts.core.world.definition.TileType
import hexrts.core.world.tile.BaseTile
import kotlin.math.sqrt

abstract class Tile(override val type: TileType) : BaseTile {
    companion object {
        const val SIZE = 32f
        const val HEIGHT = SIZE * 2
        const val AVERAGE_HEIGHT = SIZE * 1.5
        val WIDTH = SIZE * sqrt(3f)
    }

    override fun toString(): String {
        return "Tile(type=$type)"
    }
}
