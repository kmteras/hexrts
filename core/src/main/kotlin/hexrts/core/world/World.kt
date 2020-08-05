package hexrts.core.world

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch

class World {
    private val chunks = HashMap<Int, HashMap<Int, Chunk>>()

    init {
        addChunk(-1, 0)
        addPredefinedChunk(0, 0)
        addChunk(1, 0)
        addChunk(0, 1)
        addChunk(0, -1)
    }

    fun render(batch: Batch, tilemap: Texture, objectTilemap: Texture, selector: TileSelector) {
        // TODO: Add code to see if chunk is even visible

        chunks.values.forEach {
            it.values.forEach { chunk ->
                chunk.render(batch, tilemap, objectTilemap, selector)
            }
        }
    }

    fun getChunk(x: Int, y: Int): Chunk? {
        return chunks[x]?.get(y)
    }

    fun addChunk(x: Int, y: Int) {
        var yMap = chunks[x]

        if (yMap == null) {
            yMap = HashMap()
            chunks[x] = yMap
        }

        if (yMap[y] != null) {
            throw RuntimeException("Chunk already exists at ($x, $y)")
        }

        yMap[y] = Chunk(x, y)
    }

    private fun addPredefinedChunk(x: Int, y: Int) {
        var yMap = chunks[x]

        if (yMap == null) {
            yMap = HashMap()
            chunks[x] = yMap
        }

        if (yMap[y] != null) {
            throw RuntimeException("Chunk already exists at ($x, $y)")
        }

        yMap[y] = Chunk.getPredefinedChunk(x, y)
    }
}
