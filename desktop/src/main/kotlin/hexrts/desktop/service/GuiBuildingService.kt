package hexrts.desktop.service

import hexrts.core.util.TilePosition
import hexrts.core.world.BuildingService
import hexrts.core.world.World
import hexrts.core.world.tile.TerrainTile
import hexrts.desktop.screen.GameHud

class GuiBuildingService(
    private val world: World,
    private val gameHud: GameHud
) : BuildingService {
    override fun build(tilePosition: TilePosition.Global) {
        val chunk = world.getChunk(tilePosition.getChunkPosition()) ?: return
        val selectedTile = gameHud.getSelectedTile() ?: return

        chunk.setTile(tilePosition.getLocalPosition(), TerrainTile(selectedTile))
    }

    override fun deselect() {
        gameHud.deselectTile()
    }
}
