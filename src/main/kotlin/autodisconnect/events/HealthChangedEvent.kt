package autodisconnect.events

import autodisconnect.Globals.Companion.MINECRAFT_CLIENT
import autodisconnect.Globals.Companion.SETTINGS
import net.minecraft.client.gui.screen.DisconnectedScreen
import net.minecraft.client.gui.screen.TitleScreen
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.text.LiteralText
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText

class HealthChangedEvent {
    val eventType = Events.HEALTH_CHANGED

    fun handler(player: ClientPlayerEntity) {
        if (player.health.toDouble() <= SETTINGS.healthThreshold && SETTINGS.healthDisconnect) {
            MINECRAFT_CLIENT.disconnect()
            if (MINECRAFT_CLIENT.isInSingleplayer) {
                MINECRAFT_CLIENT.openScreen(
                    DisconnectedScreen(
                        MultiplayerScreen(TitleScreen()),
                        TranslatableText("disconnect.lost"),
                        TranslatableText("multiplayer.disconnect.generic")
                    )
                )
            } else {
                MINECRAFT_CLIENT.openScreen(
                    DisconnectedScreen(
                        MultiplayerScreen(TitleScreen()),
                        TranslatableText("disconnect.lost"),
                        TranslatableText("multiplayer.disconnect.generic")
                    )
                )
            }
        }
    }
}