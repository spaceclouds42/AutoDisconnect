package autodisconnect

import net.minecraft.client.MinecraftClient

class Globals {
    companion object {
        lateinit var MINECRAFT_CLIENT: MinecraftClient
        lateinit var SETTINGS: Settings
    }
}