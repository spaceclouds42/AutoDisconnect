package autodisconnect.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.DisconnectS2CPacket;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static autodisconnect.Globals.MINECRAFT_CLIENT;
import static autodisconnect.Globals.SETTINGS;
import static autodisconnect.ext.EntityExtKt.isFriend;
import static autodisconnect.ext.EntityExtKt.isFriendText;


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
        if (entity instanceof PlayerEntity) {
            determineResponse(entity);
        }
    }

    private void determineResponse(Entity e) {
        if (isFriend(e)) {
            if (SETTINGS.getDisconnectOnFriendRender()) {
                disconnect(e);
            } else if (SETTINGS.getMessageOnFriendRender()) {
                sendMessage(e);
            }
        } else {
            if (SETTINGS.getDisconnectOnPlayerRender()) {
                disconnect(e);
            } else if (SETTINGS.getMessageOnPlayerRender()) {
                sendMessage(e);
            }
        }
    }

    private void disconnect(Entity entity) {
        player.networkHandler.onDisconnect(new DisconnectS2CPacket(alertMessage.append(
                new LiteralText(isFriendText(entity))
                .append(new LiteralText(
                        "\n" + entity.getEntityName() + " entered render distance"
                ).formatted(Formatting.RED))
        )));
    }

    private void sendMessage(Entity entity) {
        player.sendSystemMessage(
                alertMessage.append(new LiteralText(isFriendText(entity))).formatted(Formatting.GREEN)
                        .append(
                        new LiteralText(entity.getEntityName() + " entered render distance")
                ).formatted(Formatting.RED),
                Util.NIL_UUID
        );
    }
}
