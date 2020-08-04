package hexrts.desktop

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import hexrts.core.util.Core
import hexrts.desktop.render.RenderScreen
import ktx.app.KtxGame

class HexRts : KtxGame<Screen>() {
    companion object {
        const val DEFAULT_WIDTH = 800
        const val DEFAULT_HEIGHT = 600

        @JvmStatic
        fun main(args: Array<String>) {
            val config = LwjglApplicationConfiguration()
            config.width = DEFAULT_WIDTH
            config.height = DEFAULT_HEIGHT
            config.title = "HexRts"
            config.forceExit = false
            val application = HexRts()
            LwjglApplication(application, config)
        }
    }

    override fun create() {
        println("Initialized ${Core.CORE}")
        addScreen(RenderScreen(DEFAULT_WIDTH.toFloat(), DEFAULT_HEIGHT.toFloat()))
        setScreen<RenderScreen>()
    }
}
