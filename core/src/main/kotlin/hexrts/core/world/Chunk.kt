package hexrts.core.world

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import hexrts.core.world.building.HomeBuilding
import hexrts.core.world.definition.TileType.*
import hexrts.core.world.tile.BaseTile
import hexrts.core.world.tile.TerrainTile
import hexrts.core.world.tile.Tile
import kotlin.math.sqrt

// TODO: Add coordinates for chunks
class Chunk(
    private val tiles: Array<Array<BaseTile>>,
    private val x: Int,
    private val y: Int
) {
    constructor(x: Int, y: Int) : this(Array<Array<BaseTile>>(CHUNK_SIZE) {
        Array(CHUNK_SIZE) { TerrainTile(Grass) }
    }, x, y)

    fun getTile(x: Int, y: Int): BaseTile {
        return tiles[y][x]
    }

    fun render(batch: Batch, tilemap: Texture, objectTilemap: Texture, tileSelector: TileSelector) {
        tiles.forEachIndexed { y, xTiles ->
            xTiles.forEachIndexed { x, tile ->
                val renderOffsetX = if (y % 2 == 0) 0f else Tile.SIZE * sqrt(3f) / 2

                val textureRegion = tile.type.getTextureRegion(tilemap)

                renderTile(batch, textureRegion, x, y)

                if (tile == tileSelector.hoveredTile) {
                    renderTile(batch, StoreBorder.getTextureRegion(tilemap), x, y)
                }

                if (tile == tileSelector.selectedTile) {
                    renderTile(batch, SandBorder.getTextureRegion(tilemap), x, y)
                }

                if (tile is TerrainTile && tile.building != null) {
                    val objectRegion = tile.building.type.getTextureRegion(objectTilemap)

                    renderTexture(batch, objectRegion,
                        x * Tile.SIZE * sqrt(3f) + renderOffsetX + Tile.SIZE / 2,
                        y * (Tile.SIZE * 1.5f - 1f) + Tile.SIZE / 2,
                        Tile.SIZE * sqrt(3f) / 2,
                        Tile.SIZE
                    )
                }
            }
        }
    }

    private fun renderTile(batch: Batch, textureRegion: TextureRegion, gridX: Int, gridY: Int) {
        renderTexture(
            batch,
            textureRegion,
            gridX * Tile.SIZE * sqrt(3f) + getRenderOffset(gridY),
            gridY * (Tile.SIZE * 1.5f - 1f),
            Tile.SIZE * sqrt(3f),
            Tile.SIZE * 2
        )
    }

    private fun renderTexture(batch: Batch, textureRegion: TextureRegion,
                              x: Float, y: Float,
                              width: Float, height: Float
    ) {
        val chunkOffsetX = this.x * CHUNK_SIZE * Tile.SIZE * sqrt(3f);
        val chunkOffsetY = this.y * CHUNK_SIZE * Tile.SIZE * sqrt(3f);

        batch.draw(
            textureRegion,
            chunkOffsetX + x,
            chunkOffsetY + y,
            width,
            height
        )
    }

    private fun getRenderOffset(y: Int): Float {
        return if (y % 2 == 0) 0f else Tile.SIZE * sqrt(3f) / 2
    }

    companion object {
        const val CHUNK_SIZE = 8

        fun getPredefinedChunk(x: Int, y: Int): Chunk {
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
                ),
                x, y
            )
        }
    }
}
