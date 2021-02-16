package autodisconnect.mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.DisconnectS2CPacket;
import net.minecraft.network.packet.s2c.play.HealthUpdateS2CPacket;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static autodisconnect.Globals.MINECRAFT_CLIENT;
import static autodisconnect.Globals.SETTINGS;

@Mixin(ClientPlayNetworkHandler.class)
abstract class ClientPlayNetworkHandlerMixin {
    @Inject(method = "onHealthUpdate", at = @At("TAIL"))
    private void updateHealth(HealthUpdateS2CPacket packet, CallbackInfo ci) {
        if (SETTINGS.getHealthDisconnectEnabled() && MINECRAFT_CLIENT.player.getHealth() <= SETTINGS.getHealthThreshold() && !MINECRAFT_CLIENT.player.isDead()) {
            MutableText disconnectMessage =
                    new LiteralText(
                            "["
                    ).formatted(Formatting.GRAY)
                            .append(
                                    new LiteralText(
                                            "AutoDisconnect"
                                    ).formatted(Formatting.DARK_AQUA)
                            )
                            .append(
                                    new LiteralText(
                                            "]\n"
                                    ).formatted(Formatting.GRAY)
                            )
                            .append(
                                    new LiteralText(
                                            "Health dropped below " + SETTINGS.getHealthThreshold() + "\nWARNING: Health disconnect has now been disabled"
                                    ).formatted(Formatting.RED)
                            );
            MINECRAFT_CLIENT.player.networkHandler.onDisconnect(new DisconnectS2CPacket(disconnectMessage));
            SETTINGS.setHealthDisconnectEnabled(false);
        }
    }
}
