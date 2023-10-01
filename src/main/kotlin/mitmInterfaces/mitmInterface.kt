package mitmInterfaces

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
import kotlin.concurrent.thread


class mitmInterface : ViewModel() {

    fun defaultStart()= runBlocking{
        var defaultThread= Thread(

                thread {
                    val path=System.getProperty("user.home")
                    val proc = CommandLine(path+"/Downloads/mitmproxy-10.0.0-windows/mitmdump.exe")
                    proc.addArgument("--listen-port")
                    proc.addArgument("7878")
                    val executor = DefaultExecutor()
                    executor.execute(proc)
                }

        )
        launch {
             defaultThread.start()

        }
    }
}