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
    val ingredient: String,
    @ColumnInfo(name = "ingredient_count")
    val count: Int,
    // String because SQLite doesn't seem to take in a Date Type, will have to be converted
    @ColumnInfo(name = "added_date")
    val dateAdded: String,
    // String because SQLite doesn't seem to take in a Date Type, will have to be converted
    @ColumnInfo(name = "expiration_date")
    val expirationDate: String,
    @ColumnInfo(name = "ingredient_location")
    val location: String
){

}
