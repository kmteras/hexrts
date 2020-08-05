package hexrts.core.exception

import hexrts.core.world.definition.ResourceType

class StorageFullException(resourceType: ResourceType) : RuntimeException() {
}
