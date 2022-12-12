package edu.towson.whatscookin.db

import androidx.room.*
import edu.towson.whatscookin.db.entities.StoredIngredient
import java.util.*

@Dao
interface IngredientDao {
    @Query("select * from stored_ingredients")
    suspend fun getIngredients(): List<StoredIngredient>

    @Query("SELECT * FROM stored_ingredients WHERE name = :name AND date_added = :dateAdded")
    suspend fun getIngredient(name: String, dateAdded: Date): StoredIngredient

    @Insert
    suspend fun addIngredient(ingredient: StoredIngredient)

    @Delete
    suspend fun deleteIngredient(ingredient: StoredIngredient)
}

@Database(entities = [StoredIngredient::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ingredientDao(): IngredientDao
}