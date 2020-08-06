package hexrts.core.world

import hexrts.core.util.TilePosition
import hexrts.core.world.tile.TerrainTile

class TileSelector(
    private val world: World
) {
    var selectedTile: TerrainTile? = null
    var hoveredTile: TerrainTile? = null

    fun hoverTile(tilePosition: TilePosition.Global) {
        hoveredTile = getChunkTile(tilePosition)
    }

    fun selectTile(tilePosition: TilePosition.Global) {
        val tile = getChunkTile(tilePosition)

        selectedTile = if (selectedTile == tile) {
            null
        } else {
            tile
        }
    }

    private fun getChunkTile(tilePosition: TilePosition.Global): TerrainTile? {
        val chunk = world.getChunk(tilePosition.getChunkPosition()) ?: return null

        // Location of the tile inside of the chunk
        val localTilePosition = tilePosition.getLocalPosition()

        if (localTilePosition.x in 0..7 && localTilePosition.y in 0..7) {
            return chunk.getTile(localTilePosition) as TerrainTile
        }

        return null
    }
}
