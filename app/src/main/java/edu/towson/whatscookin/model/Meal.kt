package edu.towson.whatscookin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// sqlite meal table
@Entity(tableName = "meals")
data class Meal(
    @PrimaryKey
    val idMeal: Int,
    @ColumnInfo(name = "recipe_name")
    val name: String,
    @ColumnInfo(name = "recipe_category")
    val category: String?,
    @ColumnInfo(name = "recipe_region")
    val region: String?,
    @ColumnInfo(name = "recipe_instructions")
    val instructions: String?,
    @ColumnInfo(name = "recipe_image_url")
    val imageUrl: String,
//    @ColumnInfo(name = "recipe_tags")
    val tags: List<String>,
    @ColumnInfo(name = "recipe_youtube_url")
    val youtubeUrl: String?,
//    @ColumnInfo(name = "recipe_measure_by_in")
    val measureByIngredient: Map<String?, String?>,
    @ColumnInfo(name = "recipe_src_url")
    val recipeSourceUrl: String?,
    @ColumnInfo(name = "recipe_image_src_url")
    val imageSourceUrl: String?,
){

}
