package data.blocker

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class blockerListModel: ViewModel() {
    private val _blockerListState= MutableStateFlow(blockerListState())
    val blockerListState: StateFlow<blockerListState> = _blockerListState.asStateFlow()

    private val _blockerState = MutableStateFlow(blockerState())
    val blockerState: StateFlow<blockerState> = _blockerState.asStateFlow()

    fun deleteValue(index: Int){
        val queryStatement= "DELETE FROM user WHERE keywords='${blockerListState.value.textlist[index]}'"
        var delete=blockerState.value.connection.prepareStatement(queryStatement)
        delete.executeUpdate()
        delete.close()

        var newList= blockerListState.value.textlist
        newList.removeAt(index)
        _blockerListState.update {
                currentState ->
            currentState.copy(textlist =newList )
        }
    }
    fun addValue(value: String){
        var newList= blockerListState.value.textlist
        newList.add(value)
        _blockerListState.update { currentState->
            currentState.copy(textlist = newList)

        }
    }
}