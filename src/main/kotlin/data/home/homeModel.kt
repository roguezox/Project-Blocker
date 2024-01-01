package data.home

import data.main.mainState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import methods.mitmInterfaces.mitmInterface
import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
import java.net.Inet4Address
import javax.swing.text.View

class homeModel: ViewModel() {

    private val _homeState = MutableStateFlow(homeState())
    val homeState: StateFlow<homeState> = _homeState.asStateFlow()

    fun defaultDisconnect() {
        mitmInterface().defaultStart().cancel()
        val proc = CommandLine("taskkill")
        proc.addArgument("/IM")
        proc.addArgument("mitmdump.exe")
        proc.addArgument("/F")

        val executor=DefaultExecutor()
        executor.execute(proc)


        _homeState.update {curentState->
            curentState.copy(isConnected = false,"Disconnected!", button = "Connect")
        }
    }

    fun defaultConnect() {
            mitmInterface().defaultStart()
        _homeState.update {curentState->
            curentState.copy(isConnected = true,"Connected to localhost: 127.0.0.1","Disconnect")
        }

    }

}