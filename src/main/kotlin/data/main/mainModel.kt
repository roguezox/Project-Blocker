package data.main

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class mainModel : ViewModel() {

    private val _mainState = MutableStateFlow(mainState())
    val mainState: StateFlow<mainState> = _mainState.asStateFlow()

    fun update(newText:String){

        _mainState.update { currentState->
            currentState.copy(search = newText)
        }

    }

    fun home(){
        _mainState.update {
                currentState->
            currentState.copy(home = true, blocker = false, restrict = false, monitor = false, settings = false, summary = false)
        }
    }

    fun blocker(){
        _mainState.update {
                currentState->
            currentState.copy(home = false, blocker = true, restrict = false, monitor = false, settings = false, summary = false)
        }
    }

    fun restrict(){
        _mainState.update {
                currentState->
            currentState.copy(home = false, blocker = false, restrict = true, monitor = false, settings = false, summary = false)
        }
    }

    fun monitor(){
        _mainState.update {
                currentState->
            currentState.copy(home = false, blocker = false, restrict = false, monitor = true, settings = false, summary = false)
        }
    }

    fun summary(){
        _mainState.update {
                currentState->
            currentState.copy(home = false, blocker = false, restrict = false, monitor = false, settings = false, summary = true)
        }
    }

    fun settings(){
        _mainState.update {
                currentState->
            currentState.copy(home = false, blocker = false, restrict = false, monitor = false, settings = true, summary = false)
        }
    }



}