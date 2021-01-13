package autodisconnect.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.network.packet.s2c.play.DisconnectS2CPacket;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static autodisconnect.Globals.MINECRAFT_CLIENT;
import static autodisconnect.Globals.SETTINGS;

@Mixin(ClientPlayerEntity.class)
abstract public class ClientPlayerEntityMixin {
    private final MutableText disconnectMessage =
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
                        "Health dropped below " + SETTINGS.getHealthThreshold()
                    ).formatted(Formatting.RED)
            );

    @Inject(method = "applyDamage", at = @At("TAIL"))
    private void updateHealth(DamageSource source, float amount, CallbackInfo ci) {
        if (MINECRAFT_CLIENT.player.getHealth() <= SETTINGS.getHealthThreshold() && SETTINGS.getHealthDisconnect()) {
            MINECRAFT_CLIENT.player.networkHandler.onDisconnect(new DisconnectS2CPacket(disconnectMessage));
        }
    }
}
