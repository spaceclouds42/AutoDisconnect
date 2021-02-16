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
import static net.minecraft.util.Util.NIL_UUID;

@Mixin(ClientPlayerEntity.class)
abstract public class ClientPlayerEntityMixin {
    @Inject(method = "applyDamage", at = @At("TAIL"))
    private void updateHealth(DamageSource source, float amount, CallbackInfo ci) {
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
                                            "Health dropped below " + SETTINGS.getHealthThreshold()
                                    ).formatted(Formatting.RED)
                            );
            MINECRAFT_CLIENT.player.networkHandler.onDisconnect(new DisconnectS2CPacket(disconnectMessage));
            SETTINGS.setHealthDisconnectEnabled(false);
        }
    }

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    private void changeSettings(String message, CallbackInfo ci) {
        if (message.equals("/autodisconnect players true")) {
            ci.cancel();
            SETTINGS.setPlayerDisconnectEnabled(true);
            MINECRAFT_CLIENT.player.sendSystemMessage(new LiteralText("Player disconnect enabled").formatted(Formatting.GREEN), NIL_UUID);
        } else if (message.equals("/autodisconnect players false")) {
            ci.cancel();
            SETTINGS.setPlayerDisconnectEnabled(false);
            MINECRAFT_CLIENT.player.sendSystemMessage(new LiteralText("Player disconnect disabled").formatted(Formatting.RED), NIL_UUID);
        } else if (message.equals("/autodisconnect health true")) {
            ci.cancel();
            SETTINGS.setHealthDisconnectEnabled(true);
            MINECRAFT_CLIENT.player.sendSystemMessage(new LiteralText("Health disconnect enabled").formatted(Formatting.GREEN), NIL_UUID);
        } else if (message.equals("/autodisconnect health false")) {
            ci.cancel();
            SETTINGS.setHealthDisconnectEnabled(false);
            MINECRAFT_CLIENT.player.sendSystemMessage(new LiteralText("Health disconnect disabled").formatted(Formatting.RED), NIL_UUID);
        } else if (message.startsWith("/autodisconnect health ")) {
            ci.cancel();
            try {
                double threshold = Double.parseDouble(message.substring(23));
                SETTINGS.setHealthThreshold(threshold);
                MINECRAFT_CLIENT.player.sendSystemMessage(new LiteralText("Health disconnect threshold set to " + threshold).formatted(Formatting.GREEN), NIL_UUID);
            } catch (NumberFormatException e) {
                MINECRAFT_CLIENT.player.sendSystemMessage(new LiteralText("Please enter a valid number").formatted(Formatting.RED), NIL_UUID);
            }
        }
    }
}
