package edu.towson.whatscookin.db

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.towson.whatscookin.db.entities.StoredIngredient
import java.util.Date

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