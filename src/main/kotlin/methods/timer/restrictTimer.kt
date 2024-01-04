package methods.timer

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.runBlocking
import java.sql.Connection
import java.sql.DriverManager
import kotlin.concurrent.thread

class restrictTimer: ViewModel() {
    fun connectToDatabase(): Connection {
        val url = "jdbc:sqlite:C:/Users/Rogue/Documents/Project-Blocker/src/data"
        return DriverManager.getConnection(url)
    }
    val connection= connectToDatabase()
    val query= "SELECT * FROM userTime"
    val initiater= connection.prepareStatement(query)
    var rs = initiater.executeQuery()
    var from: String=""
    var to: String=""
    fun restrictStart()= runBlocking {
        while(rs.next()){
            if(rs.getString("RestrictFrom")!=null&&rs.getString("RestrictTo")!=null){
                from= rs.getString("RestrictFrom")
                to = rs.getString("RestictTo")
            }
        }
        connection.close()
        rs.close()
        var restrictThread=Thread{
            thread {
                if(from==""&&to==""){

                }

            }
        }

    }

}