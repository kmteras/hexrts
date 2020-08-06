package hexrts.core.world

import hexrts.core.util.ChunkPosition
import hexrts.core.util.PerlinNoiseGenerator
import hexrts.core.definition.TileType
import hexrts.core.world.tile.BaseTile
import hexrts.core.world.tile.TerrainTile

class WorldGenerator {
    private val noiseGenerator = PerlinNoiseGenerator(0, 256 + 1)
    private val biomeNoiseGenerator = PerlinNoiseGenerator(0, 512 + 1)

    private fun getTile(x: Int, y: Int, chunkX: Int, chunkY: Int): BaseTile {
        val biomeNoise = biomeNoiseGenerator.perlin(
            x.toFloat() / Chunk.CHUNK_SIZE + chunkX + Chunk.CHUNK_SIZE * 16,
            y.toFloat() / Chunk.CHUNK_SIZE + chunkY + Chunk.CHUNK_SIZE * 16
        ) + 1 / 2f

        val noise = noiseGenerator.perlin(
            x.toFloat() / Chunk.CHUNK_SIZE + chunkX + Chunk.CHUNK_SIZE * 16,
            y.toFloat() / Chunk.CHUNK_SIZE + chunkY + Chunk.CHUNK_SIZE * 16
        ) + 1 / 2f

        if (biomeNoise < 0.45 && noise > 0.44 && noise < 0.60) {
            return TerrainTile(TileType.Water)
        }

        return when {
            noise < 0.30 -> {
                TerrainTile(TileType.Stone)
            }
            noise < 0.37 -> {
                TerrainTile(TileType.Sand)
            }
            noise < 0.45 -> {
                TerrainTile(TileType.Dirt)
            }
            noise < 0.57 -> {
                TerrainTile(TileType.Grass)
            }
            else -> {
                TerrainTile(TileType.Forest)
            }
        }
    }

    fun generateChunk(chunkX: Int, chunkY: Int): Chunk {
        val tiles = Array(Chunk.CHUNK_SIZE) {
            Array<BaseTile>(Chunk.CHUNK_SIZE) {
                TerrainTile(TileType.Grass)
            }
        }

        for (y in 0 until Chunk.CHUNK_SIZE) {
            for (x in 0 until Chunk.CHUNK_SIZE) {
                tiles[y][x] = getTile(x, y, chunkX, chunkY)
            }
        }

        return Chunk(tiles, ChunkPosition(chunkX, chunkY))
    }

    fun generateWorld(): World {
        val world = World()

        for (x in -10..10) {
            for (y in -10..10) {
                world.addChunkIfNotExists(ChunkPosition(x, y), generateChunk(x, y))
            }
        }

        return world
    }
}
