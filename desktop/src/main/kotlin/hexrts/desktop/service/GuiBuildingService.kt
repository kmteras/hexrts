package hexrts.desktop.service

import hexrts.core.util.ChunkPosition
import hexrts.core.util.TilePosition
import hexrts.core.world.BuildingService
import hexrts.core.world.World
import hexrts.core.world.tile.TerrainTile
import hexrts.desktop.screen.GameUI

class GuiBuildingService(
    private val world: World,
    private val gameUi: GameUI
) : BuildingService {
    override fun build(tilePosition: TilePosition, chunkPosition: ChunkPosition) {
        val chunk = world.getChunk(chunkPosition) ?: return
        val selectedTile = gameUi.getSelectedTile() ?: return

        chunk.setTile(tilePosition, TerrainTile(selectedTile))
    }

    override fun deselect() {
        gameUi.deselectTile()
    }
}
