package data.restrict

data class restrictState(
    val once:Boolean= false,
    val from:String="00:00",
    val to: String="00:00",
    val fromH:String="",
    val fromM: String="",
    val toH:String="",
    val toM:String=""
)