package hexrts.core.world

import hexrts.core.util.ChunkPosition
import hexrts.core.util.TilePosition
import hexrts.core.world.tile.TerrainTile
import hexrts.core.world.tile.Tile
import kotlin.math.floor

class TileSelector(
    private val world: World,
    private val buildingService: BuildingService
) {
    var selectedTile: TerrainTile? = null
    var hoveredTile: TerrainTile? = null

    fun hoverTile(x: Int, y: Int) {
        val tilePosition = getGlobalTileLocation(x, y)
        val chunkPosition = getChunkLocation(tilePosition)
        val chunk = world.getChunk(chunkPosition)

        hoveredTile = getChunkTile(tilePosition, chunk)
    }

    fun selectTile(x: Int, y: Int) {
        val tilePosition = getGlobalTileLocation(x, y)
        val chunkPosition = getChunkLocation(tilePosition)
        val chunk = world.getChunk(chunkPosition)
        val tile = getChunkTile(tilePosition, chunk)

        if (chunk != null) {
            buildingService.build(tilePosition, chunkPosition)
        }

        selectedTile = if (selectedTile == tile) {
            null
        } else {
            tile
        }
    }

    private fun getChunkTile(tilePosition: TilePosition, chunk: Chunk?): TerrainTile? {
        if (chunk == null) {
            return null
        }

        // Location of the tile inside of the chunk
        val localTilePosition = tilePosition.getLocalPosition()

        if (localTilePosition.x in 0..7 && localTilePosition.y in 0..7) {
            return chunk.getTile(localTilePosition.x, localTilePosition.y) as TerrainTile
        }

        return null
    }

    /**
     * Returns the location of the chunk the click was performed in.
     */
    private fun getChunkLocation(globalTilePosition: TilePosition): ChunkPosition {
        val chunkX = floor(globalTilePosition.x.toDouble() / Chunk.CHUNK_SIZE).toInt()
        val chunkY = floor(globalTilePosition.y.toDouble() / Chunk.CHUNK_SIZE).toInt()
        return ChunkPosition(chunkX, chunkY)
    }

    /**
     * Returns the global location of the tile the click was performed in.
     */
    private fun getGlobalTileLocation(x: Int, y: Int): TilePosition {
        val row = floor(y / (Tile.SIZE * 1.5)).toInt()
        val col: Int

        col = if (row % 2 == 0) {
            floor(x / Tile.WIDTH).toInt()
        } else {
            floor((x - Tile.WIDTH / 2) / Tile.WIDTH).toInt()
        }

        return TilePosition(col, row, TilePosition.TilePositionType.GLOBAL)
    }
}
