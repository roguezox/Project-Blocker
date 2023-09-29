package data.home

import data.main.mainState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import mitmInterfaces.mitmInterface
import javax.swing.text.View

class homeModel: ViewModel() {

    private val _homeState = MutableStateFlow(homeState())
    val homeState: StateFlow<homeState> = _homeState.asStateFlow()

    fun defaultConnect() {
        mitmInterface().defaultStart()
        _homeState.update {
                currentState->
            currentState.copy(isConnected = true)
        }
    }
}