package hexrts.core.world

import hexrts.core.world.TileType.*

class Chunk(val tiles: Array<Array<BaseTile>>) {
    companion object {
        const val CHUNK_SIZE = 8

        fun getPredefinedChunk(): Chunk {
            return Chunk(
                arrayOf(
                    arrayOf<BaseTile>(
                        TerrainTile(Sand), TerrainTile(Sand), TerrainTile(Sand), TerrainTile(Sand),
                        TerrainTile(Sand), TerrainTile(Sand), TerrainTile(Sand), TerrainTile(Sand)
                    ),
                    arrayOf<BaseTile>(
                        TerrainTile(Sand), TerrainTile(Grass), TerrainTile(Forest), TerrainTile(Dirt),
                        TerrainTile(Forest), TerrainTile(Grass), TerrainTile(Grass), TerrainTile(Sand)
                    ),
                    arrayOf<BaseTile>(
                        TerrainTile(Grass), TerrainTile(Grass), TerrainTile(Forest), TerrainTile(Dirt),
                        TerrainTile(Forest), TerrainTile(Grass), TerrainTile(Grass), TerrainTile(Grass)
                    ),
                    arrayOf<BaseTile>(
                        TerrainTile(Grass), TerrainTile(Grass), TerrainTile(Forest), TerrainTile(Dirt),
                        TerrainTile(Forest), TerrainTile(Grass), TerrainTile(Grass), TerrainTile(Grass)
                    ),
                    arrayOf<BaseTile>(
                        TerrainTile(Grass), TerrainTile(Grass), TerrainTile(Grass), TerrainTile(Forest),
                        TerrainTile(Dirt), TerrainTile(Forest), TerrainTile(Grass), TerrainTile(Grass)
                    ),
                    arrayOf<BaseTile>(
                        TerrainTile(Grass), TerrainTile(Grass), TerrainTile(Grass), TerrainTile(Forest),
                        TerrainTile(Dirt), TerrainTile(Forest), TerrainTile(Grass), TerrainTile(Grass)
                    ),
                    arrayOf<BaseTile>(
                        TerrainTile(Grass), TerrainTile(Grass), TerrainTile(Grass), TerrainTile(Forest),
                        TerrainTile(Dirt), TerrainTile(Forest), TerrainTile(Grass), TerrainTile(Grass)
                    ),
                    arrayOf<BaseTile>(
                        TerrainTile(Grass), TerrainTile(Grass), TerrainTile(Grass), TerrainTile(Grass),
                        TerrainTile(Grass), TerrainTile(Grass), TerrainTile(Grass), TerrainTile(Grass)
                    )
                )
            )
        }
    }

    constructor() : this(Array<Array<BaseTile>>(CHUNK_SIZE) {
        Array(CHUNK_SIZE) { TerrainTile(Grass) }
    })
}
