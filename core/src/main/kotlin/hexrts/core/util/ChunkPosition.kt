package hexrts.core.util

class ChunkPosition(x: Int, y: Int) : Position(x, y) {
    override fun toString(): String {
        return "ChunkPosition($x, $y)"
    }
}
