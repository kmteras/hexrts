package hexrts.core.util

import hexrts.core.util.WorldUtil.globalPositionToChunkLocal

class TilePosition(
    override val x: Int,
    override val y: Int,
    private val positionType: TilePositionType
) : Position {
    enum class TilePositionType {
        GLOBAL,
        LOCAL
    }

    fun getLocalPosition(): TilePosition {
        if (positionType == TilePositionType.LOCAL) {
            return this
        }

        return TilePosition(
            globalPositionToChunkLocal(x),
            globalPositionToChunkLocal(y),
            TilePositionType.LOCAL
        )
    }
}
