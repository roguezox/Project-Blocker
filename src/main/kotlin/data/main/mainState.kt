package data.main

data class mainState (
    val search: String="",
    val home: Boolean= true,
    val blocker:Boolean=false,
    val monitor: Boolean=false,
    val restrict: Boolean= false,
    val summary: Boolean=false,
    val settings: Boolean=false,
    val invalid: Boolean=false,
)