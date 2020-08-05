package hexrts.core.world.building

import hexrts.core.world.definition.BuildingType

abstract class Building(override val type: BuildingType) : BaseBuilding {
    private val features = HashSet<BuildingFeature>()

    protected fun addFeature(feature: BuildingFeature) {
        features.add(feature)
    }

    override fun update(delta: Float) {

    }
}
