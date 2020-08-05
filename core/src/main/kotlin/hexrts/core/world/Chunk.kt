package hexrts.core.world

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import hexrts.core.world.building.HomeBuilding
import hexrts.core.world.definition.TileType.*
import hexrts.core.world.tile.BaseTile
import hexrts.core.world.tile.TerrainTile
import hexrts.core.world.tile.Tile
import kotlin.math.sqrt

// TODO: Add coordinates for chunks
class Chunk(private val tiles: Array<Array<BaseTile>>) {
    companion object {
        const val CHUNK_SIZE = 8

        fun getPredefinedChunk(): Chunk {
            return Chunk(
                arrayOf(
                    arrayOf<BaseTile>(
                        TerrainTile(Sand),
                        TerrainTile(Sand),
                        TerrainTile(Sand),
                        TerrainTile(Sand),
                        TerrainTile(Sand),
                        TerrainTile(Sand),
                        TerrainTile(Sand),
                        TerrainTile(Sand)
                    ),
                    arrayOf<BaseTile>(
                        TerrainTile(Sand),
                        TerrainTile(Grass),
                        TerrainTile(Forest),
                        TerrainTile(Dirt),
                        TerrainTile(Forest),
                        TerrainTile(Grass),
                        TerrainTile(Grass),
                        TerrainTile(Sand)
                    ),
                    arrayOf<BaseTile>(
                        TerrainTile(Grass),
                        TerrainTile(Grass),
                        TerrainTile(Forest),
                        TerrainTile(Dirt),
                        TerrainTile(Forest),
                        TerrainTile(Grass),
                        TerrainTile(Grass),
                        TerrainTile(Grass)
                    ),
                    arrayOf<BaseTile>(
                        TerrainTile(Grass),
                        TerrainTile(Grass),
                        TerrainTile(Forest),
                        TerrainTile(Dirt),
                        TerrainTile(Forest),
                        TerrainTile(Grass),
                        TerrainTile(Grass),
                        TerrainTile(Grass)
                    ),
                    arrayOf<BaseTile>(
                        TerrainTile(Grass),
                        TerrainTile(Grass),
                        TerrainTile(Grass),
                        TerrainTile(Forest),
                        TerrainTile(Dirt),
                        TerrainTile(Forest),
                        TerrainTile(Grass),
                        TerrainTile(Grass)
                    ),
                    arrayOf<BaseTile>(
                        TerrainTile(Grass),
                        TerrainTile(Grass),
                        TerrainTile(Grass),
                        TerrainTile(Forest),
                        TerrainTile(Dirt),
                        TerrainTile(Forest),
                        TerrainTile(Grass),
                        TerrainTile(Grass)
                    ),
                    arrayOf<BaseTile>(
                        TerrainTile(Grass),
                        TerrainTile(Grass),
                        TerrainTile(Grass),
                        TerrainTile(Forest),
                        TerrainTile(Dirt, HomeBuilding()),
                        TerrainTile(Forest),
                        TerrainTile(Grass),
                        TerrainTile(Grass)
                    ),
                    arrayOf<BaseTile>(
                        TerrainTile(Grass),
                        TerrainTile(Grass),
                        TerrainTile(Grass),
                        TerrainTile(Grass),
                        TerrainTile(Grass),
                        TerrainTile(Grass),
                        TerrainTile(Grass),
                        TerrainTile(Grass)
                    )
                )
            )
        }
    }

    constructor() : this(Array<Array<BaseTile>>(CHUNK_SIZE) {
        Array(CHUNK_SIZE) { TerrainTile(Grass) }
    })

    fun getTile(x: Int, y: Int): BaseTile {
        return tiles[y][x]
    }

    fun render(batch: Batch, tilemap: Texture, objectTilemap: Texture, tileSelector: TileSelector) {
        tiles.forEachIndexed { y, xTiles ->
            xTiles.forEachIndexed { x, tile ->
                val renderOffsetX = if (y % 2 == 0) 0f else Tile.SIZE * sqrt(3f) / 2

                val textureRegion = tile.type.getTextureRegion(tilemap)

                batch.draw(
                    textureRegion,
                    x * Tile.SIZE * sqrt(3f) + renderOffsetX,
                    y * (Tile.SIZE * 1.5f - 1f),
                    Tile.SIZE * sqrt(3f),
                    Tile.SIZE * 2
                )

                if (tile == tileSelector.hoveredTile) {
                    val hoverRegion = StoreBorder.getTextureRegion(tilemap)

                    batch.draw(
                        hoverRegion,
                        x * Tile.SIZE * sqrt(3f) + renderOffsetX,
                        y * (Tile.SIZE * 1.5f - 1f),
                        Tile.SIZE * sqrt(3f),
                        Tile.SIZE * 2
                    )
                }

                if (tile is TerrainTile && tile.building != null) {
                    val objectRegion = tile.building.type.getTextureRegion(objectTilemap)

                    batch.draw(
                        objectRegion,
                        x * Tile.SIZE * sqrt(3f) + renderOffsetX + Tile.SIZE / 2,
                        y * (Tile.SIZE * 1.5f - 1f) + Tile.SIZE / 2,
                        Tile.SIZE * sqrt(3f) / 2,
                        Tile.SIZE
                    )
                }
            }
        }
    }
}
