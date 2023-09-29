package mitmInterfaces

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
import kotlin.concurrent.thread


class mitmInterface : ViewModel() {

    fun defaultStart()= runBlocking{
        var defaultThread= Thread(

                thread {
                    val path=System.getProperty("user.home")
                    val proc = CommandLine(path+"/mitmdump.exe")
                    proc.addArgument("--listen-port")
                    proc.addArgument("8082")
                    val executor = DefaultExecutor()
                    executor.execute(proc)
                    print(executor)
                }

        )
        launch {
             defaultThread.start()
        }
    }
}