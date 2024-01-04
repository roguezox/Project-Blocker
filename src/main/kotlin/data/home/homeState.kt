package data.home

data class homeState(
    val isConnected: Boolean= false,
    val status: String= "Disconnected!",
    val button: String= "Connect",
    val withRestrict: Boolean=false,
    val withKeywords:Boolean= false
)