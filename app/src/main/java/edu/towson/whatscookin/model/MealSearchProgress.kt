package edu.towson.whatscookin.model

// This class is used by the recipe screen progress indicator
// It assists in tracking the meal search progress
data class MealSearchProgress(
    val checkedIngredientCount: Int = 0,
    val totalIngredientCount: Int = 0,
    val isSearchFinished: Boolean = true,
) {
    fun getProgressFraction(): Float{
        return if (totalIngredientCount <= 0){
            1f
        } else{
            checkedIngredientCount / totalIngredientCount.toFloat()
        }
    }
}