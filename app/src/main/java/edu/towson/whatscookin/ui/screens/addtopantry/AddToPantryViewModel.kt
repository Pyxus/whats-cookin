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
    private val _selectedIngredients: MutableSet<Int> = mutableSetOf()
    private val _ingredients: MutableState<List<StoredIngredient>> = mutableStateOf(listOf())

    val ingredients: State<List<StoredIngredient>> = _ingredients
    val allIngredients : State<List<Ingredient>> = _allIngredients

    init {
        viewModelScope.launch {
            withContext(
                Dispatchers.IO
            ) {
                _allIngredients.value = theMealDB.getAllIngredients()
            }
        }
    }
}