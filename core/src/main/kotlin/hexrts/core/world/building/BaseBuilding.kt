package hexrts.core.world.building

import hexrts.core.gameobject.Updatable
import hexrts.core.definition.BuildingType

interface BaseBuilding : Updatable {
    val type: BuildingType
}
