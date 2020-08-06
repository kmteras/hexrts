package hexrts.core.world

import hexrts.core.util.ChunkPosition
import hexrts.core.util.TilePosition

interface BuildingService {
    fun build(tilePosition: TilePosition, chunkPosition: ChunkPosition)

    fun deselect();
}
