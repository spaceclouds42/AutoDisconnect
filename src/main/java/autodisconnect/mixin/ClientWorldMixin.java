package autodisconnect.mixin;

import autodisconnect.events.Events;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static autodisconnect.Globals.EVENT_BUS;

@Mixin(ClientWorld.class)
abstract public class ClientWorldMixin {
    @Inject(method = "addEntityPrivate", at = @At("TAIL"))
    private void addEntityPrivate(int id, Entity entity, CallbackInfo ci) {
        if (entity instanceof PlayerEntity) {
            EVENT_BUS.publish(Events.PLAYER_RENDERED, entity);
        }
    }
}
