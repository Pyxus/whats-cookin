package edu.towson.whatscookin.ui.screens.addtopantry

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import edu.towson.whatscookin.db.entities.StoredIngredient
import edu.towson.whatscookin.model.Ingredient
import edu.towson.whatscookin.network.TheMealDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class AddToPantryViewModel(app: Application) : AndroidViewModel(app) {

    private val theMealDB = TheMealDB()

    private val _allIngredients: MutableState<List<Ingredient>> = mutableStateOf(listOf())
    val allIngredients: State<List<Ingredient>> = _allIngredients

    private val _selectedIngredients: MutableState<Set<Int>> = mutableStateOf(setOf())
    val selectedIngredients: State<Set<Int>> = _selectedIngredients

    private val _ingredientsToStore = mutableStateOf(listOf<StoredIngredient>())
    val ingredientsToStore: State<List<StoredIngredient>> = _ingredientsToStore

    var searchText = mutableStateOf("")


    init {
        viewModelScope.launch {
            withContext(
                Dispatchers.IO
            ) {
                _allIngredients.value = theMealDB.getAllIngredients()
            }
        }
    }

    fun unselectAll() {
        _selectedIngredients.value = setOf()
        _ingredientsToStore.value = listOf()
    }

    fun prepSelectedIngredientsStorage() {
        _ingredientsToStore.value = _selectedIngredients.value.map { id ->
            val ingredient = _allIngredients.value.find { ing -> ing.id == id }
            StoredIngredient(
                name = ingredient!!.name,
                count = 1,
                storageLocation = StoredIngredient.Pantry
            )
        }
    }

    fun setIngredientToStoreCount(storedIngredient: StoredIngredient, count: Int) {
        _ingredientsToStore.value = _ingredientsToStore.value.map { si ->
            if (si.id == storedIngredient.id) {
                StoredIngredient(
                    id = storedIngredient.id,
                    dateAdded = storedIngredient.dateAdded,
                    name = storedIngredient.name,
                    storageLocation = storedIngredient.storageLocation,
                    count = count
                )
            } else {
                si
            }
        }
    }

    fun setIngredientToStoreLocation(storedIngredient: StoredIngredient, location: String) {
        _ingredientsToStore.value = _ingredientsToStore.value.map { si ->
            if (si.id == storedIngredient.id) {
                StoredIngredient(
                    id = storedIngredient.id,
                    dateAdded = storedIngredient.dateAdded,
                    name = storedIngredient.name,
                    storageLocation = location,
                    count = storedIngredient.count
                )
            } else {
                si
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

}