package autodisconnect

data class Settings(
    var playerDisconnectEnabled: Boolean = false,
    var healthDisconnectEnabled: Boolean = false,
    var healthThreshold: Double = 10.0,
)
