package edu.towson.whatscookin.ui.screens.recipie

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import edu.towson.whatscookin.model.Ingredient
import edu.towson.whatscookin.model.Meal

class RecipeScreenViewModel: ViewModel() {

    private val _meals = mutableStateOf(listOf<Meal>())
    val meals: State<List<Meal>> = _meals

    fun updateMeals(){
        //TODO: Loop through all ingredients in the pantry

        //TODO: Perform a mealDB meal query
    }
}