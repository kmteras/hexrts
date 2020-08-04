package hexrts.desktop.input

import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.OrthographicCamera
import java.lang.Float.max
import java.lang.Float.min

class ScreenInputProcessor(private val camera: OrthographicCamera
) : InputProcessor {
    companion object {
        const val MIN_SCROLL = 0.2f;
        const val MAX_SCROLL = 2f;

        const val SCROLL_SPEED = 0.2f;
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        return false
    }

    override fun keyTyped(character: Char): Boolean {
        return false
    }

    override fun scrolled(amount: Int): Boolean {
        if (amount > 0) {
            camera.zoom = min(MAX_SCROLL, camera.zoom + SCROLL_SPEED)
        } else {
            camera.zoom = max(MIN_SCROLL, camera.zoom - SCROLL_SPEED)
        }
        return false
    }

    override fun keyUp(keycode: Int): Boolean {
        return false
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        return false
    }

    override fun keyDown(keycode: Int): Boolean {
        return false
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return false
    }
}
