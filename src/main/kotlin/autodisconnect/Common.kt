package autodisconnect

import autodisconnect.Globals.Companion.EVENT_BUS
import autodisconnect.Globals.Companion.MINECRAFT_CLIENT
import autodisconnect.Globals.Companion.SETTINGS
import autodisconnect.config.Config
import autodisconnect.events.EventBus
import autodisconnect.events.Events
import autodisconnect.events.HealthChangedEvent
import net.fabricmc.api.ModInitializer
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity

object Common : ModInitializer {
    override fun onInitialize() {
        MINECRAFT_CLIENT = MinecraftClient.getInstance()
        EVENT_BUS = EventBus()
        SETTINGS = Config.loadConfig()
        registerEvents()
    }

    private fun registerEvents() {
        EVENT_BUS.subscribe(Events.HEALTH_CHANGED, ::healthChangedHandler)
    }

    // Handlers
    private fun healthChangedHandler(x: Any) {
        HealthChangedEvent().handler(x as ClientPlayerEntity)
    }
}
