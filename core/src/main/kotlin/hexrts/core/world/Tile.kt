package hexrts.core.world

abstract class Tile(override val type: TileType) : BaseTile {
    companion object {
        const val SIZE = 32f
    }
}
