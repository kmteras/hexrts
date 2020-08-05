package hexrts.core.world.tile

import hexrts.core.gameobject.Updatable
import hexrts.core.world.definition.TileType

interface BaseTile : Updatable {
    val type: TileType
}
