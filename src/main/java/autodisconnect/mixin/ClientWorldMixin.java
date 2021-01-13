package autodisconnect.mixin;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
abstract public class ClientWorldMixin {
    @Inject(method = "addEntityPrivate", at = @At("TAIL"))
    private void addEntityPrivate(int id, Entity entity, CallbackInfo ci) {
        if (entity instanceof PlayerEntity) {
            System.out.println("OH MY GOD THERE's A PLAYER!!!!!! aaaaaaahhhhhhhhh");
        }
    }
}
