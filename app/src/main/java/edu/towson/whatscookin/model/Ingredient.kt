package edu.towson.whatscookin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class Ingredient(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "ingredient_name")
    val name: String,
    @ColumnInfo(name = "ingredient_description")
    val description: String?,
    @ColumnInfo(name = "ingredient_image_url")
    val imageUrl: String?

    ){

}