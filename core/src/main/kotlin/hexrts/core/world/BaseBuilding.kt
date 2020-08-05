package hexrts.core.world

import hexrts.core.gameobject.Updatable
import hexrts.core.world.definition.BuildingType

interface BaseBuilding : Updatable {
    val type: BuildingType
}
