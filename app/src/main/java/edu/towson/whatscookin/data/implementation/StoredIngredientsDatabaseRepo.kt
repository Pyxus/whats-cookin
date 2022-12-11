package edu.towson.whatscookin.data.implementation

import android.app.Application
import androidx.compose.runtime.State
import androidx.room.Room
import edu.towson.whatscookin.data.InterfaceStoredIngredientsDatabase
import edu.towson.whatscookin.data.StoredIngredientsDatabase
import edu.towson.whatscookin.model.Ingredient
import edu.towson.whatscookin.model.StoredIngredient


class StoredIngredientsDatabaseRepo(wc_app: Application, ingredients: State<List<StoredIngredient>>) : InterfaceStoredIngredientsDatabase {

    private val sidb: StoredIngredientsDatabase

    init {
        sidb = Room.databaseBuilder(
            wc_app,
            StoredIngredientsDatabase::class.java,
            "ingredient_store"
        ).build()

//        ingredients.forEach {
//            ingredient: Ingredient -> sidb.storedIngredientsDao().addIngredient(ingredient)
//        }
    }

    override suspend fun addIngredient(ingredient: StoredIngredient) {
        sidb.storedIngredientsDao().addIngredient(ingredient)
    }

    override suspend fun deleteIngredient(idx: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getIngredients(): List<StoredIngredient> {
        return sidb.storedIngredientsDao().getIngredients()
    }

}