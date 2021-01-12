package autodisconnect

import autodisconnect.config.Config
import net.fabricmc.api.ModInitializer
import net.minecraft.client.MinecraftClient


object Common : ModInitializer {
    private lateinit var settings: Config

    override fun onInitialize() {
        Config.init()
        settings = Config.SETTINGS

        events()
    }

    private fun events() {

    }
}
