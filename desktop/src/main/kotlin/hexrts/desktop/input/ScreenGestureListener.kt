package hexrts.desktop.input

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.input.GestureDetector
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import hexrts.core.world.TileSelector

class ScreenGestureListener(
    private val camera: OrthographicCamera,
    private val selector: TileSelector
) : GestureDetector.GestureListener {
    override fun fling(velocityX: Float, velocityY: Float, button: Int): Boolean {
        return false
    }

    override fun zoom(initialDistance: Float, distance: Float): Boolean {
        return false
    }

    override fun pan(x: Float, y: Float, deltaX: Float, deltaY: Float): Boolean {
        camera.translate(-deltaX * camera.zoom, deltaY * camera.zoom)
        return false
    }

    override fun pinchStop() {
    }

    override fun tap(x: Float, y: Float, count: Int, button: Int): Boolean {
        val worldCoords = camera.unproject(Vector3(x, y, 0f))
        selector.selectTile(worldCoords.x.toInt(), worldCoords.y.toInt())
        return false
    }

    override fun panStop(x: Float, y: Float, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun longPress(x: Float, y: Float): Boolean {
        return false
    }

    override fun touchDown(x: Float, y: Float, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun pinch(
        initialPointer1: Vector2?,
        initialPointer2: Vector2?,
        pointer1: Vector2?,
        pointer2: Vector2?
    ): Boolean {
        return false
    }
}
