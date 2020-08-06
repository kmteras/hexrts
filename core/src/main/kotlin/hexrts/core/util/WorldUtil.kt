package hexrts.core.util

import hexrts.core.world.Chunk

object WorldUtil {
    fun globalTilePositionToChunkLocal(x: Int, y: Int): Pair<Int, Int> {
        return Pair(globalPositionToChunkLocal(x), (y))
    }

    fun globalPositionToChunkLocal(position: Int): Int {
        return if (position >= 0) {
            position % Chunk.CHUNK_SIZE
        } else {
            if (position % Chunk.CHUNK_SIZE == 0) {
                0
            } else {
                Chunk.CHUNK_SIZE + position % Chunk.CHUNK_SIZE
            }
        }
    }
}
