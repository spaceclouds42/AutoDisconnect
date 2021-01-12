package autodisconnect

import autodisconnect.config.Config
import autodisconnect.events.EventBus
import net.minecraft.client.MinecraftClient

class Globals {
    companion object {
        lateinit var MINECRAFT_CLIENT: MinecraftClient
        lateinit var EVENT_BUS: EventBus
        lateinit var SETTINGS: Config
    }
}