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
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ApplicationViewModel(app: Application) : AndroidViewModel(app) {
    private val db: AppDatabase

    private val _ingredients = mutableStateOf(listOf<StoredIngredient>())
    val ingredient: State<List<StoredIngredient>> = _ingredients

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


}