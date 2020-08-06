package hexrts.core.util

import hexrts.core.util.WorldUtil.globalPositionToChunkLocal

sealed class TilePosition(
    override val x: Int,
    override val y: Int
) : Position {
    class Local(x: Int, y: Int) : TilePosition(x, y) {

    }

    class Global(x: Int, y: Int) : TilePosition(x, y) {

    }

    fun getLocalPosition(): Local {
        if (this is Local) {
            return this
        }

        return Local(
            globalPositionToChunkLocal(x),
            globalPositionToChunkLocal(y)
        )
    }
}
