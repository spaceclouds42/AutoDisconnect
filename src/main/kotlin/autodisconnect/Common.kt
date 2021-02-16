package autodisconnect

import autodisconnect.Globals.Companion.MINECRAFT_CLIENT
import autodisconnect.Globals.Companion.SETTINGS
import net.fabricmc.api.ModInitializer
import net.minecraft.client.MinecraftClient

object Common : ModInitializer {
    override fun onInitialize() {
        MINECRAFT_CLIENT = MinecraftClient.getInstance()
        SETTINGS = Settings()
    }
}
