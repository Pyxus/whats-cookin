package edu.towson.whatscookin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Ingredient(

    val id: Int,
    val name: String,
    val description: String?,
    val imageUrl: String?

    ){

}