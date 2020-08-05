package hexrts.core.util

import kotlin.math.pow
import kotlin.random.Random

class PerlinNoiseGenerator(seed: Long, val scale: Int) {
    private val random = Random(seed)
    private val grid = generateGradientGrid()

    private fun generateGradientGrid(): Array<Array<Pair<Float, Float>>> {
        val gradient = Array(scale) {
            Array(scale) {
                Pair(0f, 0f)
            }
        }

        for (y in 0 until scale) {
            for (x in 0 until scale) {
                gradient[y][x] = Pair(random.nextFloat(), random.nextFloat())
            }
        }

        return gradient
    }

    private fun lerp(a: Float, b: Float, w: Float): Float {
        return a + w * (b - a)
    }

    private fun fade(t: Float): Float {
        return 6 * t.pow(5) - 15 * t.pow(4) + 10 * t.pow(3)
    }

    private fun dotGridGradient(xInt: Int, yInt: Int, x: Float, y: Float): Float {
        val dx = x - xInt
        val dy = y - yInt

        return dx * grid[yInt][xInt].first + dy * grid[yInt][xInt].second
    }

    fun perlin(x: Float, y: Float): Float {
        val x0 = x.toInt()
        val x1 = x0 + 1
        val y0 = y.toInt()
        val y1 = y0 + 1

        val dx = x - x0
        val dy = y - y0

        val nx0 = dotGridGradient(x0, y0, x, y)
        val nx1 = dotGridGradient(x1, y0, x, y)
        val ix1 = lerp(nx0, nx1, dx)

        val ny0 = dotGridGradient(x0, y1, x, y)
        val ny1 = dotGridGradient(x1, y1, x, y)
        val ix2 = lerp(ny0, ny1, dx)

        return lerp(ix1, ix2, dy)
    }
}
