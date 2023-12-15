package data.blocker

import androidx.compose.runtime.mutableStateListOf

data class blockerListState(
    val textlist: MutableList<String> = mutableStateListOf<String>()
)