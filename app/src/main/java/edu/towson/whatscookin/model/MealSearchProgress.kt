package edu.towson.whatscookin.model

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