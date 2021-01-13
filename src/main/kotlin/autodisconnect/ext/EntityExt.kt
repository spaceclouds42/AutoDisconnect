package autodisconnect.ext

import autodisconnect.Globals.Companion.SETTINGS
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity

fun Entity.isFriendText(): String {
    if (this.isFriend()) {
        return "(Friend) "
    }
    return ""
}

fun Entity.isFriend(): Boolean {
    return this is PlayerEntity && SETTINGS.friendList.contains(this.entityName)
}