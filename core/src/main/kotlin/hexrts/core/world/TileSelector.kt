package hexrts.core.world

import hexrts.core.world.tile.TerrainTile
import hexrts.core.world.tile.Tile
import kotlin.math.sqrt

class TileSelector(private val world: World) {
    var selectedTile: TerrainTile? = null
    var hoveredTile: TerrainTile? = null

    fun hoverTile(x: Int, y: Int) {
        val tile = findTileLocation(x, y)

        hoveredTile = if (tile.first in 0..7 && tile.second in 0..7) {
            world.getChunk(0, 0).getTile(tile.first, tile.second) as TerrainTile
        } else {
            null
        }
    }

    fun selectTile(x: Int, y: Int) {
        println("Selecting tile at $x $y")

        val tile = findTileLocation(x, y)

        selectedTile = if (tile.first in 0..7 && tile.second in 0..7) {
            val newSelected = world.getChunk(0, 0).getTile(tile.first, tile.second) as TerrainTile

            if (selectedTile == newSelected) {
                null
            } else {
                newSelected
            }
        } else {
            null
        }
    }

    private fun findTileLocation(x: Int, y: Int): Pair<Int, Int> {
        val row = kotlin.math.floor(y / (Tile.SIZE * 1.5)).toInt()
        val col: Int

        val tileWidth = Tile.SIZE * sqrt(3f)

        col = if (row % 2 == 0) {
            kotlin.math.floor(x / tileWidth).toInt()
        } else {
            kotlin.math.floor((x - tileWidth / 2) / tileWidth).toInt()
        }

        return Pair(col, row)
    }
}
