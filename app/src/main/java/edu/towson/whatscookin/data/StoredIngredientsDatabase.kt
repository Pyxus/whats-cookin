package edu.towson.whatscookin.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface StoredIngredientsDao {

    // Return names of all stored ingredients
    @Query("select stored_ingredient from ingredient_store")
    fun getAllIngredientsName(): List<String>

    // Return names of all stored ingredients which have expired
    // Need to lookup how to check against current day
    @Query("select stored_ingredient from ingredient_store")
    fun getAllExpiredIngredients(): List<String>

    // Delete all stored ingredients which have expired
    // Need to lookup how to check against current day
    @Query("delete from ingredient_store")
    fun removeExpiredIngredients()

}

