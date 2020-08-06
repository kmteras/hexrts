package hexrts.core.world

import hexrts.core.util.TilePosition
import hexrts.core.world.tile.TerrainTile

class TileSelector(
    private val world: World
) {
    var selectedTilePosition: TilePosition.Global? = null
    var hoveredTilePosition: TilePosition.Global? = null

    fun hoverTile(tilePosition: TilePosition.Global) {
        hoveredTilePosition = tilePosition
    }

    fun selectTile(tilePosition: TilePosition.Global) {
        selectedTilePosition = if (selectedTilePosition == tilePosition) {
            null
        } else {
            tilePosition
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
