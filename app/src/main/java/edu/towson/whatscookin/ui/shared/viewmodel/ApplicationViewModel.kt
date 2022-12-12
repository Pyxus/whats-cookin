package edu.towson.whatscookin.ui.shared.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import edu.towson.whatscookin.db.AppDatabase
import edu.towson.whatscookin.db.IngredientDao
import edu.towson.whatscookin.db.entities.StoredIngredient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ApplicationViewModel(app: Application) : AndroidViewModel(app) {
    private val db: AppDatabase

    private val _ingredients = mutableStateOf(listOf<StoredIngredient>())
    val ingredients: State<List<StoredIngredient>> = _ingredients

    init {
        db = Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "app.db"
        ).build()

        viewModelScope.launch {
            _ingredients.value = db.ingredientDao().getIngredients()
        }
    }

    // Takes a list of ingredients and inserts them into the database
    // An optional onFinished can be supplied to trigger something when this is done
    fun addIngredients(ingredients: List<StoredIngredient>, onFinished: () -> Unit = {}){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                ingredients.forEach { ingredient ->
                    db.ingredientDao().addIngredient(ingredient)
                }
                _ingredients.value = db.ingredientDao().getIngredients()
                onFinished()
            }
        }
    }

    // Takes a list of ingredients and removes them from the database
    // An optional onFinished can be supplied to trigger something when this is done
    fun deleteIngredients(ingredients: List<StoredIngredient>, onFinished: () -> Unit = {}){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                ingredients.forEach { ingredient ->
                    db.ingredientDao().deleteIngredient(ingredient)
                }
                _ingredients.value = db.ingredientDao().getIngredients()
                onFinished()
            }
        }
    }

    // Returns a list of all ingredients stored in the database
    suspend fun getIngredients(): List<StoredIngredient>{
        return db.ingredientDao().getIngredients()
    }
}