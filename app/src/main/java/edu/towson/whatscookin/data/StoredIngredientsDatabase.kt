package edu.towson.whatscookin.data

import androidx.room.*
import edu.towson.whatscookin.model.StoredIngredient

@Dao
interface StoredIngredientsDao {

    // Return names of all stored ingredients
    @Query("SELECT stored_ingredient FROM stored_ingredients")
    fun getAllIngredientsName(): List<String>

    // Return names of all stored ingredients which have expired
    @Query("SELECT stored_ingredient FROM stored_ingredients WHERE expiration_date < DATE()")
    fun getAllExpiredIngredients(): List<String>

    // Delete all stored ingredients which have expired
    // Need to lookup how to check against current day
    @Delete()
    fun removeExpiredIngredients(ingredientToDelete: StoredIngredient)

}

@Database(entities = [StoredIngredient::class], version = 2, exportSchema = false)
abstract class StoredIngredientsDatabase : RoomDatabase() {
    abstract fun storedIngredientsDao(): StoredIngredientsDao
}

