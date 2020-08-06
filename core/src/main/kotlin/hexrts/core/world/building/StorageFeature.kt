package hexrts.core.world.building

import hexrts.core.exception.StorageFullException
import hexrts.core.definition.ResourceType

class StorageFeature(private val capacity: Long) : BuildingFeature {
    private val storage = HashMap<ResourceType, Long>()

    fun getStorage(): Map<ResourceType, Long> {
        return storage
    }

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
