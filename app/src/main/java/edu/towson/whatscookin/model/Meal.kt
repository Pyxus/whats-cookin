package edu.towson.whatscookin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Meal(
    val idMeal: Int,
    val name: String,
    val category: String?,
    val region: String?,
    val instructions: String?,
    val imageUrl: String,
    val tags: List<String>,
    val youtubeUrl: String?,
    val measureByIngredient: Map<String?, String?>,
    val recipeSourceUrl: String?,
    val imageSourceUrl: String?,
){

}
