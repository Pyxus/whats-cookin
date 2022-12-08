package edu.towson.whatscookin.ui.screens.recipie

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.towson.whatscookin.model.Ingredient
import edu.towson.whatscookin.model.Meal
import edu.towson.whatscookin.network.TheMealDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeScreenViewModel : ViewModel() {

    private val _mealDb = TheMealDB()

    private val _meals = mutableStateOf(listOf<Meal>())
    val meals: State<List<Meal>> = _meals

    fun updateMeals() {
        //TODO: Loop through all ingredients in the pantry
        val ingredients = listOf(Ingredient("Egg", null, "", 0))

        viewModelScope.launch {
            _meals.value = withContext(Dispatchers.IO) {
                val potentialMeals = mutableMapOf<Int, PotentialMeal>()
                ingredients.forEach { ingredient ->
                    _mealDb.searchByIngredient(ingredient.name).forEach { meal ->
                        val pm = potentialMeals[meal.idMeal]
                        if (pm != null) {
                            pm.possessedIngredientCount++;
                        } else {
                            potentialMeals[meal.idMeal] = PotentialMeal(meal)
                        }
                    }
                }

                val pmValues = potentialMeals.values
                pmValues.sortedByDescending { pm ->
                    pm.possessedIngredientCount
                }

                pmValues.map { pm ->
                    pm.meal
                }
            }
        }

        _meals.value.forEach {
            Log.d("Test", it.name)
        }
    }

    private class PotentialMeal(meal: Meal) {
        var possessedIngredientCount = 1
        val meal: Meal

        init {
            this.meal = meal
        }
    }
}