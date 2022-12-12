package edu.towson.whatscookin.ui.screens.pantry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PantryScreenViewModel: ViewModel() {

    enum class StorageFilter {
        ALL, FRIDGE, FREEZER, PANTRY
    }

    var selectedFilter by mutableStateOf(StorageFilter.ALL)
    val searchText = mutableStateOf("")

}