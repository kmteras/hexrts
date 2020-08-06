package hexrts.core.world.building

import hexrts.core.definition.BuildingType

abstract class Building(override val type: BuildingType) : BaseBuilding {
    private val features = HashMap<Class<out BuildingFeature>, BuildingFeature>()

    protected fun addFeature(feature: BuildingFeature) {
        features[feature.javaClass] = feature
    }

    fun getFeature(featureType: Class<out BuildingFeature>): BuildingFeature? {
        return features[featureType]
    }

    fun getStorageFeature(): StorageFeature? {
        return getFeature(StorageFeature::class.java) as StorageFeature
    }

    override fun update(delta: Float) {

    }

    override fun toString(): String {
        return "Building(type=$type, features=$features)"
    }
}
