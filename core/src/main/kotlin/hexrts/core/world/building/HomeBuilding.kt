package hexrts.core.world.building

import hexrts.core.definition.BuildingType
import hexrts.core.definition.ResourceType

class HomeBuilding : Building(BuildingType.Base) {
    init {
        val storage = StorageFeature(1000)
        storage.addStorage(ResourceType.Wood, 100)
        addFeature(storage)
    }

    override fun update(delta: Float) {

    }
}
