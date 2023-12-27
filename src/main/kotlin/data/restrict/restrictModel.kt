package data.restrict

import data.blocker.blockerState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class restrictModel: ViewModel() {
    private val _restrictState= MutableStateFlow(restrictState())
    val restrictState: StateFlow<restrictState> = _restrictState.asStateFlow()


    private val _blockerState = MutableStateFlow(blockerState())
    val blockerState: StateFlow<blockerState> = _blockerState.asStateFlow()

    fun once(){
        _restrictState.update {it ->
            it.copy(once = true)
        }
    }
    fun updateRun(from:String, to:String){
        _restrictState.update { it->
            it.copy(
                from=from,
                to=to
            )
        }
    }
    fun savefrom(from:String){
        val statement = "UPDATE user SET RestrictFrom ='$from' WHERE "+ "\"index\"" +" =1"
        val prep= blockerState.value.connection.prepareStatement(statement)
        prep.execute()
        prep.close()

        _restrictState.update { it->
            it.copy(
                from = from,
            )
        }


    }

    fun saveto(to:String){
        val statement = "UPDATE user SET RestrictTo ='$to' WHERE "+ "\"index\"" +" =1"
        val prep= blockerState.value.connection.prepareStatement(statement)
        prep.execute()
        prep.close()
        _restrictState.update {it ->
            it.copy(to=to)
        }
    }


}