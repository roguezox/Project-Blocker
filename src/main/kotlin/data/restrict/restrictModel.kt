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
                to=to,
                fromH = from.split(":")[0],
                fromM = from.split(":")[1],
                toH = to.split(":")[0],
                toM = to.split(":")[1]
            )
        }
    }
    fun savefrom(from:String){
        val statement = "UPDATE userTime SET RestrictFrom ='$from' WHERE "+ "\"index\"" +" =1"
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


        val statement = "UPDATE userTime SET RestrictTo ='$to' WHERE "+ "\"index\"" +" =1"
        val prep= blockerState.value.connection.prepareStatement(statement)
        prep.execute()
        prep.close()
        _restrictState.update {it ->
            it.copy(to=to)
        }
    }

    fun quickShiftFrom(newtext:String){
        val statement = "UPDATE userTime SET RestrictFrom ='$newtext' WHERE "+ "\"index\"" +" =1"
        val prep= blockerState.value.connection.prepareStatement(statement)
        prep.execute()
        prep.close()
        _restrictState.update { it ->
            it.copy(from=newtext)
        }
    }
    fun quickShiftTo(newtext: String){
        val statement = "UPDATE userTime SET RestrictTo ='$newtext' WHERE "+ "\"index\"" +" =1"
        val prep= blockerState.value.connection.prepareStatement(statement)
        prep.execute()
        prep.close()
        _restrictState.update { it ->
            it.copy(
                to = newtext
            )
        }
    }




}

