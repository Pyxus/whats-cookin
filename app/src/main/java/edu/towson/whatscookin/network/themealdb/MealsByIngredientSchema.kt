package edu.towson.whatscookin.network.themealdb

data class MealsByIngredientSchema(
    val meals: List<MealByIngredientSchema>
) {
}

data class MealByIngredientSchema(
    val strMeal: String,
    val strMealThumb: String,
    val idMeal: Int,
){
}