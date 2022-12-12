package edu.towson.whatscookin.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "stored_ingredients", primaryKeys = ["id", "date_added"])
data class StoredIngredient(
    @ColumnInfo
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "date_added")
    val dateAdded: Date = Date(),

    @ColumnInfo()
    val name: String,

    @ColumnInfo()
    val count: Int,

    @ColumnInfo()
    val storageLocation: String,
){
    companion object {
        const val Fridge = "fridge"
        const val Freezer = "freezer"
        const val Pantry = "pantry"
    }
}