package hexrts.core.util

import com.badlogic.gdx.math.Vector3
import hexrts.core.util.WorldUtil.globalPositionToChunkLocal
import hexrts.core.world.Chunk
import hexrts.core.world.tile.Tile
import kotlin.math.floor

sealed class TilePosition(x: Int, y: Int) : Position(x, y) {
    class Local(x: Int, y: Int) : TilePosition(x, y) {
        override fun toString(): String {
            return "LocalTilePosition($x, $y)"
        }
    }

    class Global(x: Int, y: Int) : TilePosition(x, y) {
        companion object {
            fun fromLocalTileAndChunkPosition(local: Local, chunkPosition: ChunkPosition): Global {
                return Global(
                    chunkPosition.x + local.x * Chunk.CHUNK_SIZE,
                    chunkPosition.y + local.y * Chunk.CHUNK_SIZE
                )
            }
        }

        /**
         * Returns the location of the chunk based on global tile position.
         */
        fun getChunkPosition(): ChunkPosition {
            val chunkX = floor(x.toDouble() / Chunk.CHUNK_SIZE).toInt()
            val chunkY = floor(y.toDouble() / Chunk.CHUNK_SIZE).toInt()
            return ChunkPosition(chunkX, chunkY)
        }

        fun getWorldVector(): Vector3 {
            val worldX = if (y % 2 == 0) {
                x * Tile.WIDTH - Tile.WIDTH / 2
            } else {
                x * Tile.WIDTH
            }

            return Vector3(
                worldX,
                y * Tile.AVERAGE_HEIGHT,
                0f
            )
        }

        override fun toString(): String {
            return "GlobalTilePosition($x, $y)"
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
