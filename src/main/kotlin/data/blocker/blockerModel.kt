package data.blocker

import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class blockerModel: ViewModel() {
    private val _blockerState = MutableStateFlow(blockerState())
    val blockerState: StateFlow<blockerState> = _blockerState.asStateFlow()



    fun updateKeywords(newText:String) {
        _blockerState.update {
            currentState ->
            currentState.copy(keywordsFieldState = newText)
        }
    }
    fun updateRun() {
        _blockerState.update {currentState ->
            currentState.copy(once = true)

        }
    }
    fun reset(){
        _blockerState.update {currentState ->
            currentState.copy(once = false)

        }
    }




}