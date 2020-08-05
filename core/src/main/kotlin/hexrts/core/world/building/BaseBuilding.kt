package hexrts.core.world.building

import hexrts.core.gameobject.Updatable
import hexrts.core.world.definition.BuildingType

interface BaseBuilding : Updatable {
    val type: BuildingType
}
