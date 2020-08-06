package hexrts.core.util

import hexrts.core.world.Chunk
import hexrts.core.world.tile.Tile
import kotlin.math.floor

object WorldUtil {
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

    /**
     * Returns the global location of the tile based on in-game pixels.
     */
    fun getGlobalTileLocation(x: Int, y: Int): TilePosition.Global {
        val row = floor(y / (Tile.SIZE * 1.5)).toInt()
        val col: Int

        col = if (row % 2 == 0) {
            floor(x / Tile.WIDTH).toInt()
        } else {
            floor((x - Tile.WIDTH / 2) / Tile.WIDTH).toInt()
        }

        return TilePosition.Global(col, row)
    }
}
