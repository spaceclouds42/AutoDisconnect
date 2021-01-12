package autodisconnect.mixin;

import autodisconnect.events.Events;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static autodisconnect.Globals.EVENT_BUS;

@Mixin(ClientPlayerEntity.class)
abstract public class ClientPlayerEntityMixin {
    @Inject(method = "applyDamage", at = @At("TAIL"))
    private void updateHealth(DamageSource source, float amount, CallbackInfo ci) {
        EVENT_BUS.publish(Events.HEALTH_CHANGED, (ClientPlayerEntity) (Object) this);
    }
}
