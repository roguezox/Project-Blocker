package data.restrict

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class restrictModel: ViewModel() {
    private val _restrictState= MutableStateFlow(restrictState())
    val restrictState: StateFlow<restrictState> = _restrictState.asStateFlow()



}