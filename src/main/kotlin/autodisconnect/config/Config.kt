package autodisconnect.config

import autodisconnect.Common
import net.minecraft.entity.player.PlayerEntity

data class Config
(
    var messageOnPlayerRender: Boolean = true,

    var disconnectOnPlayerRender: Boolean = true,

    var healthDisconnect: Boolean = false,

    var healthThreshold: Double = 10.0,

    var stayWhenFriendRender: Boolean = true,

    val friendList: List<PlayerEntity> = listOf(),
) {
    companion object {
        lateinit var SETTINGS: Config

        fun init() {
            SETTINGS = loadConfig()
        }

        private fun loadConfig(): Config {
            return Config()
        }
    }



    private fun createConfig() {

    }
}
