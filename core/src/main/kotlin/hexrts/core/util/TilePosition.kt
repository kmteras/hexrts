package hexrts.core.util

import hexrts.core.util.WorldUtil.globalPositionToChunkLocal
import hexrts.core.world.Chunk
import kotlin.math.floor

sealed class TilePosition(
    override val x: Int,
    override val y: Int
) : Position {
    class Local(x: Int, y: Int) : TilePosition(x, y) {

    }

    class Global(x: Int, y: Int) : TilePosition(x, y) {
        /**
         * Returns the location of the chunk based on global tile position.
         */
        fun getChunkPosition(): ChunkPosition {
            val chunkX = floor(x.toDouble() / Chunk.CHUNK_SIZE).toInt()
            val chunkY = floor(y.toDouble() / Chunk.CHUNK_SIZE).toInt()
            return ChunkPosition(chunkX, chunkY)
        }
    }

    fun getLocalPosition(): Local {
        if (this is Local) {
            return this
        }

        return Local(
            globalPositionToChunkLocal(x),
            globalPositionToChunkLocal(y)
        )
    }
}
