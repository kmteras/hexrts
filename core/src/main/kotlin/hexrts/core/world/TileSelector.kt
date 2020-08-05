package hexrts.core.world

import hexrts.core.world.tile.TerrainTile
import hexrts.core.world.tile.Tile
import kotlin.math.floor

class TileSelector(private val world: World) {
    var selectedTile: TerrainTile? = null
    var hoveredTile: TerrainTile? = null

    fun hoverTile(x: Int, y: Int) {
        val location = findTileLocation(x, y)
        hoveredTile = getChunkTile(location.first, location.second)
    }

    fun selectTile(x: Int, y: Int) {
        val location = findTileLocation(x, y)
        val tile = getChunkTile(location.first, location.second)

        selectedTile = if (selectedTile == tile) {
            null
        } else {
            tile
        }
    }

    private fun getChunkTile(x: Int, y: Int): TerrainTile? {
        val chunkX = floor(x.toDouble() / Chunk.CHUNK_SIZE).toInt()
        val chunkY = floor(y.toDouble() / Chunk.CHUNK_SIZE).toInt()

        val chunkTileX = if (x >= 0) x % Chunk.CHUNK_SIZE else if (x % Chunk.CHUNK_SIZE == 0) 0 else Chunk.CHUNK_SIZE + x % Chunk.CHUNK_SIZE
        val chunkTileY = if (y >= 0) y % Chunk.CHUNK_SIZE else if (y % Chunk.CHUNK_SIZE == 0) 0 else Chunk.CHUNK_SIZE + y % Chunk.CHUNK_SIZE

        if (chunkTileX in 0..7 && chunkTileY in 0..7) {
            val chunk = world.getChunk(chunkX, chunkY)

            if (chunk != null) {
                return chunk.getTile(chunkTileX, chunkTileY) as TerrainTile
            }
        }

        return null
    }

    private fun findTileLocation(x: Int, y: Int): Pair<Int, Int> {
        val row = floor(y / (Tile.SIZE * 1.5)).toInt()
        val col: Int

        col = if (row % 2 == 0) {
            floor(x / Tile.WIDTH).toInt()
        } else {
            floor((x - Tile.WIDTH / 2) / Tile.WIDTH).toInt()
        }

        return Pair(col, row)
    }
}
