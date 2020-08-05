package hexrts.core.world

enum class TileType(val x: Int, val y: Int, val width: Int, val height: Int) {
    Grass(610, 142, 120, 140),
    Dirt(732, 710, 120, 140),
    Forest(488, 994, 120, 140),
    Sand(244, 426, 120, 140)
}
