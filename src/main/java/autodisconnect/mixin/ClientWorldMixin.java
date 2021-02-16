package autodisconnect.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
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


@Mixin(ClientWorld.class)
abstract public class ClientWorldMixin {
    private final MutableText alertMessage =
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
                                    "]"
                            ).formatted(Formatting.GRAY)
                    );
    private final ClientPlayerEntity player = MINECRAFT_CLIENT.player;

    @Inject(method = "addEntityPrivate", at = @At("TAIL"))
    private void addEntityPrivate(int id, Entity entity, CallbackInfo ci) {
        if (entity instanceof PlayerEntity && SETTINGS.getPlayerDisconnectEnabled()) {
            disconnect(entity);
        }
    }

    private void disconnect(Entity entity) {
        if (player == null) { return; }
        player.networkHandler.onDisconnect(new DisconnectS2CPacket(alertMessage.append(
                new LiteralText(
                        "\n" + entity.getEntityName() + " entered render distance"
                ).formatted(Formatting.RED)
        )));
    }
}
