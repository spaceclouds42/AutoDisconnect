package autodisconnect.extension

import net.minecraft.entity.player.PlayerEntity
import autodisconnect.Globals.Companion.SETTINGS

class PlayerEntityExt {
    fun PlayerEntity.isHealthBelowThreshold(): Boolean {
        return this.health.toDouble() <= SETTINGS.healthThreshold
    }
}