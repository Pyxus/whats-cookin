package edu.towson.whatscookin.ui.screens.addtopantry

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.towson.whatscookin.model.Ingredient
import edu.towson.whatscookin.model.StoredIngredient
import edu.towson.whatscookin.network.TheMealDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddToPantryViewModel : ViewModel() {

    private val theMealDB = TheMealDB()

    private val _allIngredients: MutableState<List<Ingredient>> = mutableStateOf(listOf())
    private val _selectedIngredients: MutableState<Set<Int>> = mutableStateOf(setOf())
    private val _ingredients: MutableState<List<StoredIngredient>> = mutableStateOf(listOf())
    var searchText = mutableStateOf("")

    val ingredients: State<List<StoredIngredient>> = _ingredients
    val allIngredients: State<List<Ingredient>> = _allIngredients
    val selectedIngredients: State<Set<Int>> = _selectedIngredients

    init {
        viewModelScope.launch {
            withContext(
                Dispatchers.IO
            ) {
                _allIngredients.value = theMealDB.getAllIngredients()
            }
        }
    }

    fun toggleSelection(id: Int) {

        if (_selectedIngredients.value.contains(id)) {
            _selectedIngredients.value =
                _selectedIngredients.value.filter { element -> element != id }.toSet()
        } else {
            _selectedIngredients.value = _selectedIngredients.value + setOf(id)
        }
    }

    //fun setIngredient(){}
    //fun setCount(){}
}