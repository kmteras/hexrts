package hexrts.core.world

import hexrts.core.util.TilePosition

interface BuildingService {
    fun build(tilePosition: TilePosition.Global)

    fun deselect();
}
