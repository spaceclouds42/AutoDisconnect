package autodisconnect.config

data class Config
(
    var healthDisconnect: Boolean = true,

    var healthThreshold: Double = 10.0,

    var messageOnPlayerRender: Boolean = true,

    var disconnectOnPlayerRender: Boolean = false,

    var messageOnFriendRender: Boolean = true,

    var disconnectOnFriendRender: Boolean = false,

    val friendList: List<String> = listOf(),
)
{
    companion object {
        fun loadConfig(): Config {
            return Config()
        }
    }
}
