package hexrts.desktop.service

import hexrts.core.util.TilePosition
import hexrts.core.world.BuildingService
import hexrts.core.world.World
import hexrts.core.world.building.Building
import hexrts.core.world.building.CollectionBuilding
import hexrts.core.world.tile.TerrainTile
import hexrts.desktop.screen.GameHud

class GuiBuildingService(
    private val world: World,
    private val gameHud: GameHud
) : BuildingService {
    override fun build(tilePosition: TilePosition.Global) {
        val tile = world.getTile(tilePosition) ?: return
        val buildingType = gameHud.getSelectedBuildingType() ?: return

        tile as TerrainTile
        if (!buildingType.canBuildOn(tile)) {
            return
        }
        tile.building = CollectionBuilding(buildingType)
    }

    override fun deselect() {
        gameHud.deselectTile()
    }
}
