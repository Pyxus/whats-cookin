package edu.towson.whatscookin.ui.screens.pantry

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import edu.towson.whatscookin.db.entities.StoredIngredient

class PantryScreenViewModel : ViewModel() {

    enum class StorageFilter {
        ALL, FRIDGE, FREEZER, PANTRY
    }

    private val _ingredientsSelectedForDeletion = mutableStateOf(listOf<StoredIngredient>())
    val ingredientsSelectedForDeletion: State<List<StoredIngredient>> =
        _ingredientsSelectedForDeletion

    var selectedFilter by mutableStateOf(StorageFilter.ALL)
    val searchText = mutableStateOf("")
    val isDeleteInitiated = mutableStateOf(false)

    fun clearDeleteQueue(){
        _ingredientsSelectedForDeletion.value = listOf()
        isDeleteInitiated.value = false
    }

    fun enqueueDeletion(storedIngredient: StoredIngredient) {
        if (!_ingredientsSelectedForDeletion.value.contains(storedIngredient)) {
            _ingredientsSelectedForDeletion.value =
                _ingredientsSelectedForDeletion.value + listOf(storedIngredient)
        }
    }

    fun dequeueDeletion(storedIngredient: StoredIngredient) {
        _ingredientsSelectedForDeletion.value = _ingredientsSelectedForDeletion.value.filter { si -> si != storedIngredient }
        if (_ingredientsSelectedForDeletion.value.isEmpty()){
            isDeleteInitiated.value = false
        }
    }

    fun toggleDeletion(storedIngredient: StoredIngredient){
        if (isDeleteInitiated.value) {
            if (_ingredientsSelectedForDeletion.value.contains(storedIngredient)){
                dequeueDeletion(storedIngredient)
            } else{
                enqueueDeletion(storedIngredient)
            }
        }
    }
}