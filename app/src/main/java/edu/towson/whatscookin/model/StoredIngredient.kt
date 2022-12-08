package edu.towson.whatscookin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

// sqlite stored ingredient table
@Entity(tableName = "stored_ingredients")
data class StoredIngredient(
    @PrimaryKey(autoGenerate = true)
    val ingredientId: Int,
    @ColumnInfo(name = "stored_ingredient")
    val ingredient: Ingredient,
    @ColumnInfo(name = "ingredient_count")
    val count: Int,
    @ColumnInfo(name = "added_date")
    val dateAdded: Date,
    @ColumnInfo(name = "expiration_date")
    val expirationDate: Date,
    @ColumnInfo(name = "ingredient_location")
    val location: String
){

}
