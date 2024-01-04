package methods.mitmInterfaces
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor

import java.sql.Connection
import java.sql.DriverManager
import kotlin.concurrent.thread


class mitmInterface() : ViewModel() {



    fun connectToDatabase(): Connection {
        val url = "jdbc:sqlite:C:/Users/Rogue/Documents/Project-Blocker/src/data"
        return DriverManager.getConnection(url)
    }
    val connection= connectToDatabase()
    val query= "SELECT * FROM user"
    val initiater= connection.prepareStatement(query)
    var rs = initiater.executeQuery()
    var keywords= listOf<String>()

    fun defaultStart(keyword:Boolean)= runBlocking{

        while (rs.next()){
            keywords+=rs.getString("keywords")

        }
        connection.close()
        rs.close()
        var defaultThread= Thread(

                thread {
                    print(keyword)

                    if (!keyword){
                        val path=System.getProperty("user.home")
                        val proc = CommandLine(path+"/Downloads/mitmproxy-10.0.0-windows/mitmdump.exe")
                        proc.addArgument("--listen-port")
                        proc.addArgument("7880")
                        val executor = DefaultExecutor()
                        executor.execute(proc)
                    } else if(keyword){
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
                    else{

                    }
                }

        )
        launch {
             defaultThread.start()

        }
    }
}