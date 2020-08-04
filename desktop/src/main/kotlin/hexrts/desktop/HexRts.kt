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
        @JvmStatic
        fun main(args: Array<String>) {
            val config = LwjglApplicationConfiguration()
            config.width = 800
            config.height = 600
            config.title = "HexRts"
            val application = HexRts()
            LwjglApplication(application, config)
        }
    }

    override fun create() {
        println("${Gdx.files}")

        println("Initialized ${Core.CORE}")
        addScreen(RenderScreen())
        setScreen<RenderScreen>()
    }
}
