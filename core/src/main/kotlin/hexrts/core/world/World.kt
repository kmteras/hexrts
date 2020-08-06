package hexrts.core.world

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import hexrts.core.util.ChunkPosition
import hexrts.core.util.TilePosition
import hexrts.core.world.tile.BaseTile

class World {
    private val chunks = HashMap<Int, HashMap<Int, Chunk>>()

    init {
        addPredefinedChunk(0, 0)
    }

    fun render(batch: Batch, tilemap: Texture, objectTilemap: Texture, selector: TileSelector) {
        // TODO: Add code to see if chunk is even visible

        chunks.values.forEach {
            it.values.forEach { chunk ->
                chunk.render(batch, tilemap, objectTilemap, selector)
            }
        }
    }

    private fun getChunk(x: Int, y: Int): Chunk? {
        return chunks[x]?.get(y)
    }

    fun getChunk(chunkPosition: ChunkPosition): Chunk? {
        return getChunk(chunkPosition.x, chunkPosition.y)
    }

    fun getTile(tilePosition: TilePosition.Global): BaseTile? {
        return getChunk(tilePosition.getChunkPosition())?.getTile(tilePosition.getLocalPosition())
    }

    private fun addChunk(x: Int, y: Int, chunkGetter: (Int, Int) -> Chunk) {
        var yMap = chunks[x]

        if (yMap == null) {
            yMap = HashMap()
            chunks[x] = yMap
        }

        if (yMap[y] != null) {
            throw RuntimeException("Chunk already exists at ($x, $y)")
        }

        yMap[y] = chunkGetter(x, y)
    }

    private fun addPredefinedChunk(x: Int, y: Int) {
        addChunk(x, y) { xx: Int, yy: Int ->
            Chunk.getPredefinedChunk(ChunkPosition(xx, yy))
        }
    }

    fun addChunkIfNotExists(chunkPosition: ChunkPosition, chunk: Chunk) {
        val existingChunk = getChunk(chunkPosition)

        if (existingChunk == null) {
            addChunk(chunkPosition.x, chunkPosition.y) { _, _ ->
                chunk
            }
        }
    }
}
