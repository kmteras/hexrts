package hexrts.core.world

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch

class World {
    private val chunks = arrayListOf(Chunk(-1, 0), Chunk.getPredefinedChunk(0, 0), Chunk.getPredefinedChunk(1, 0))

    fun render(batch: Batch, tilemap: Texture, objectTilemap: Texture, selector: TileSelector) {
        chunks.forEach {
            it.render(batch, tilemap, objectTilemap, selector)
        }
    }

    fun getChunk(x: Int, y: Int): Chunk {
        return chunks[1]
    }
}
