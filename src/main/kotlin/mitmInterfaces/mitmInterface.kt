package mitmInterfaces

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import data.blocker.blockerModel
import data.home.homeState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
import java.net.Inet4Address
import java.sql.Connection
import java.sql.DriverManager
import kotlin.concurrent.thread


class mitmInterface : ViewModel() {

    fun connectToDatabase(): Connection {
        val url = "jdbc:sqlite:C:/Users/Rogue/Documents/Project-Blocker/src/data"
        return DriverManager.getConnection(url)
    }
    val connection= connectToDatabase()
    val query= "SELECT * FROM user"
    val initiater= connection.prepareStatement(query)
    var rs = initiater.executeQuery()
    var keywords= listOf<String>()

    fun defaultStart()= runBlocking{
        while (rs.next()){
            keywords+=rs.getString("keywords")

        }
        connection.close()
        var defaultThread= Thread(

                thread {

                    if (keywords.isEmpty() ){
                        val path=System.getProperty("user.home")
                        val proc = CommandLine(path+"/Downloads/mitmproxy-10.0.0-windows/mitmdump.exe")
                        proc.addArgument("--listen-port")
                        proc.addArgument("7878")
                        val executor = DefaultExecutor()
                        executor.execute(proc)
                    } else{
                        val path=System.getProperty("user.home")
                        val proc = CommandLine(path+"/Downloads/mitmproxy-10.0.0-windows/mitmdump.exe")

                        proc.addArgument("--listen-port")
                        proc.addArgument("7880")

                        proc.addArgument("-s")
                        proc.addArgument("$path/Videos/script.py")
                        proc.addArgument("--set")
                        proc.addArgument(" keywords=${keywords.joinToString(" ")}")
                        val executor = DefaultExecutor()
                        executor.execute(proc)
                    }
                }

        )
        launch {
             defaultThread.start()

        }
    }
}