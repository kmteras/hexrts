package hexrts.core.world.building

import hexrts.core.exception.StorageFullException
import hexrts.core.world.definition.ResourceType

class StorageFeature(private val capacity: Long) : BuildingFeature {
    private val storage = HashMap<ResourceType, Long>()

    fun addStorage(resourceType: ResourceType, amount: Long) {
        val currentAmount = storage.getOrElse(resourceType, { 0 })
        if (currentAmount + amount > capacity) {
            storage[resourceType] = capacity
            throw StorageFullException(resourceType)
        }

        storage[resourceType] = currentAmount + amount
    }

    override fun toString(): String {
        return "StorageFeature(capacity=$capacity, storage=$storage)"
    }
}
