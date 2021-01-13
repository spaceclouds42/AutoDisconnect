package autodisconnect

import autodisconnect.Globals.Companion.MINECRAFT_CLIENT
import autodisconnect.Globals.Companion.SETTINGS
import autodisconnect.config.Config
import net.fabricmc.api.ModInitializer
import net.minecraft.client.MinecraftClient

object Common : ModInitializer {
    override fun onInitialize() {
        MINECRAFT_CLIENT = MinecraftClient.getInstance()
        SETTINGS = Config.loadConfig()
    }
}
