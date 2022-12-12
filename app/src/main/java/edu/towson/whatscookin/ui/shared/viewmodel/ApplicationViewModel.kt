package edu.towson.whatscookin.ui.shared.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import edu.towson.whatscookin.db.AppDatabase
import edu.towson.whatscookin.db.IngredientDao
import edu.towson.whatscookin.db.entities.StoredIngredient
import kotlinx.coroutines.launch
import java.util.*

class ApplicationViewModel(app: Application) : AndroidViewModel(app) {
    private val db: AppDatabase

    init {
        db = Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "app.db"
        ).build()


        viewModelScope.launch {
            getIngredientsDao().addIngredient(StoredIngredient(name = "TestIngredient", count = 1, dateAdded = Date()))
            Log.d("Test", Date().toString())
            getIngredientsDao().getIngredients().forEach { si ->
                Log.d("Test", si.name)
            }
        }
    }

    fun getIngredientsDao(): IngredientDao{
        return db.ingredientDao()
    }
}