package edu.towson.whatscookin.data

import androidx.room.*
import edu.towson.whatscookin.model.Ingredient
import edu.towson.whatscookin.model.StoredIngredient

@Dao
interface StoredIngredientsDao {

    @Insert
    fun addIngredient(ingredient: StoredIngredient)

    @Delete
    fun deleteIngredients(ingredient: StoredIngredient)

    // Return names of all stored ingredients
    @Query("SELECT * FROM stored_ingredients")
    fun getIngredients(): List<StoredIngredient>

}

@Database(entities = [StoredIngredient::class], version = 2, exportSchema = false)
abstract class StoredIngredientsDatabase : RoomDatabase() {
    abstract fun storedIngredientsDao(): StoredIngredientsDao
}

