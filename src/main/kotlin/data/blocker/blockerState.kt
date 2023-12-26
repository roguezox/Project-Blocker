package data.blocker

import androidx.compose.runtime.mutableStateListOf
import java.sql.Connection
import java.sql.DriverManager

data class blockerState(
    val keywordsFieldState: String="",
    val connection: Connection= connectToDatabase(),
    val once: Boolean = false

)

fun connectToDatabase(): Connection {
    val url = "jdbc:sqlite:C:/Users/Rogue/Documents/Project-Blocker/src/data"
    return DriverManager.getConnection(url)
}

