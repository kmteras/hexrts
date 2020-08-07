package hexrts.core.util

import hexrts.core.world.Chunk
import hexrts.core.world.tile.Tile
import kotlin.math.abs
import kotlin.math.round
import kotlin.math.sqrt

object WorldUtil {
    fun globalPositionToChunkLocal(position: Int): Int {
        return if (position >= 0) {
            position % Chunk.CHUNK_SIZE
        } else {
            if (position % Chunk.CHUNK_SIZE == 0) {
                0
            } else {
                Chunk.CHUNK_SIZE + position % Chunk.CHUNK_SIZE
            }
        }
    }

    /**
     * Returns the global location of the tile based on in-game pixels.
     *
     * The following code is based on https://www.redblobgames.com/grids/hexagons/
     */
    fun getGlobalTileLocation(x: Float, y: Float): TilePosition.Global {
        val offsetX = x - Tile.WIDTH / 2
        val offsetY = y - Tile.AVERAGE_HEIGHT / 2

        // Screen to axial coordinates
        val q = (sqrt(3f) / 3f * offsetX - 1f/3f * offsetY) / Tile.SIZE
        val r = 2f/3f * offsetY / Tile.SIZE

        return roundToGlobalPosition(q, r)
    }

    fun axialToCubeCoordinates(hex: Pair<Float, Float>): Triple<Float, Float, Float> {
        val x = hex.first
        val z = hex.second
        val y = -x-z
        return Triple(x, y, z)
    }

    fun cubeCoordinatesToOddRow(cube: Triple<Int, Int, Int>): TilePosition.Global {
        val x = cube.first + (cube.third - (abs(cube.third) % 2)) / 2
        val y = cube.third
        return TilePosition.Global(x, y)
    }

    fun roundToGlobalPosition(x: Float, y: Float): TilePosition.Global {
        val cube = axialToCubeCoordinates(Pair(x, y))

        var rx = round(cube.first).toInt()
        var ry = round(cube.second).toInt()
        var rz = round(cube.third).toInt()

        val xDiff = abs(rx - cube.first)
        val yDiff = abs(ry - cube.second)
        val zDiff = abs(rz - cube.third)

        if (xDiff > yDiff && xDiff > zDiff) {
            rx = -ry-rz
        } else if (yDiff > zDiff) {
            ry = -rx-rz
        } else {
            rz = -rx-ry
        }

        return cubeCoordinatesToOddRow(Triple(rx, ry, rz))
    }
}
