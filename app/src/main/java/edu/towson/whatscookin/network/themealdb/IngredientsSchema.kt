package edu.towson.whatscookin.network.themealdb

data class IngredientsSchema(
    val meals: List<IngredientSchema>
)

data class IngredientSchema(
    val idIngredient: Int,
    val strIngredient: String,
    val strDescription: String?,
    val strType: String,
)