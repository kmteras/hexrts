package hexrts.core.util

abstract class Position(val x: Int, val y: Int) {
    override fun toString(): String {
        return "Position($x, $y)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (other == null) {
            return false
        }

        if (javaClass != other.javaClass) {
            return false
        }

        other as Position

        return x == other.x && y == other.y
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}
