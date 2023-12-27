package data.restrict

data class restrictState(
    val once:Boolean= false,
    val from:String="00:00",
    val to: String="00:00"
)