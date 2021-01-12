package autodisconnect.config

import net.minecraft.entity.player.PlayerEntity

data class Config
(
    var messageOnPlayerRender: Boolean = true,

    var disconnectOnPlayerRender: Boolean = true,

    var healthDisconnect: Boolean = true,

    var healthThreshold: Double = 10.0,

    var stayWhenFriendRender: Boolean = true,

    val friendList: List<PlayerEntity> = listOf(),
)
{
    companion object {
        fun loadConfig(): Config {
            return Config()
        }
        private fun createConfig() {

        }
    }
}
