package data.home

import data.blocker.blockerState
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

    private val _blockerState = MutableStateFlow(blockerState())
    val blockerState: StateFlow<blockerState> = _blockerState.asStateFlow()


    fun updateRun(keyword:Int,restrict:Int){
        if(keyword==0){
            _homeState.update {curentState->
                curentState.copy(withKeywords = false)
            }
        }else if(keyword==1){
            _homeState.update {curentState->
                curentState.copy(withKeywords = true)
            }
        }

        if(restrict==0){
            _homeState.update {curentState->
                curentState.copy(withRestrict = false)
            }
        }else if(restrict==1){
            _homeState.update {curentState->
                curentState.copy(withRestrict = true)
            }
        }
    }

     fun defaultDisconnect(keywords: Boolean) {
        mitmInterface().defaultStart(keywords).cancel()
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

    fun defaultConnect(keywords:Boolean) {
            mitmInterface().defaultStart(keywords)
        _homeState.update {curentState->
            curentState.copy(isConnected = true,"Connected to localhost: 127.0.0.1","Disconnect")
        }

    }

    fun keywordConnect(){
        val init= "Select * from user"
        val initprep= blockerState.value.connection.prepareStatement(init)
        val res= initprep.executeQuery()

        var initIndex=0
        while (res.next()){
            initIndex=res.getInt("index")
            break
        }
        initprep.close()
        val statementt = "UPDATE user SET withKeywords =1 WHERE "+ "\"index\"" +" =$initIndex"
        val prep= blockerState.value.connection.prepareStatement(statementt)
        prep.executeUpdate()
        prep.close()


        _homeState.update {currentState->
            currentState.copy(withKeywords = true)

        }
    }
    fun keywordDisconnect(){
        val init= "Select * FROM user"
        val initprep= blockerState.value.connection.prepareStatement(init)
        val res= initprep.executeQuery()

        var initIndex=9

        while (res.next()){
            initIndex=res.getInt("index")

            break
        }
        initprep.close()

        val statementt = "UPDATE user SET withKeywords =0 WHERE "+ "\"index\"" +" =$initIndex"
        val prep= blockerState.value.connection.prepareStatement(statementt)
        prep.executeUpdate()
        prep.close()

        _homeState.update {curentState->
            curentState.copy(withKeywords = false)
        }
    }
    fun timeConnect(it:Boolean){
        val init= "Select * from user"
        val initprep= blockerState.value.connection.prepareStatement(init)
        val res= initprep.executeQuery()

        var initIndex:Int=0
        while (res.next()){
            initIndex=res.getInt("index")
            break
        }
        initprep.close()
        val statement = "UPDATE user SET withRestrict =1 WHERE "+ "\"index\"" +" =$initIndex"
        val prep= blockerState.value.connection.prepareStatement(statement)
        prep.executeUpdate()
        prep.close()
        _homeState.update {curentState->
            curentState.copy(withRestrict = it)
        }
    }

    fun timeDisconnect(){
        val init= "Select * from user"
        val initprep= blockerState.value.connection.prepareStatement(init)
        val res= initprep.executeQuery()

        var initIndex:Int=0
        while (res.next()){
            initIndex=res.getInt("index")
            break
        }
        initprep.close()
        val statement = "UPDATE user SET withRestrict =0 WHERE "+ "\"index\"" +" =$initIndex"
        val prep= blockerState.value.connection.prepareStatement(statement)
        prep.executeUpdate()
        prep.close()
        _homeState.update {curentState->
            curentState.copy(withRestrict = false)
        }
    }

}