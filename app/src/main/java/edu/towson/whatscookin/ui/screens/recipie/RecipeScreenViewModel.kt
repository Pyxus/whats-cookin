package edu.towson.whatscookin.ui.screens.recipie

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.towson.whatscookin.model.Ingredient
import edu.towson.whatscookin.model.Meal
import edu.towson.whatscookin.network.TheMealDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class RecipeScreenViewModel : ViewModel() {

    private val _mealDb = TheMealDB()

    private val _meals = mutableStateOf(listOf<Meal>())
    val meals: State<List<Meal>> = _meals

    private val _mealImages = mutableStateOf(mapOf<Int, ImageBitmap>())
    val mealImages: State<Map<Int, ImageBitmap>> = _mealImages

    //private val _selectedMeal = mutableStateOf<Meal?>(null)
    var selectedMeal: Meal? = null

    fun updateMeals() {
        val ingredients = listOf(
            Ingredient("Egg", null, "", 0),
            Ingredient("Milk", null, "", 0),
            Ingredient("Butter", null, "", 0),
            Ingredient("Bacon", null, "", 0),
            Ingredient("Plain Flour", null, "", 0),
        )

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
                val sortedValues = pmValues.sortedByDescending { pm ->
                    pm.possessedIngredientCount
                }

                sortedValues.map { pm ->
                    pm.meal
                }
            }

            fetchMealImages()
        }

        _meals.value.forEach {
            Log.d("Test", it.imageUrl)
        }
    }

    private suspend fun fetchMealImages() {
        withContext(Dispatchers.IO){
            val client = OkHttpClient()

            _meals.value.forEach { meal ->
                var bitmap: Bitmap? = null

                val request = Request.Builder()
                    .get()
                    .url(meal.imageUrl)
                    .build()
                val response = client.newCall(request).execute()
                val responseBody = response.body
                if (responseBody != null){
                    bitmap = BitmapFactory.decodeStream(responseBody.byteStream())
                    if (bitmap != null)
                    {
                        val mealImagesById = mutableMapOf<Int, ImageBitmap>()
                        mealImagesById[meal.idMeal] = bitmap.asImageBitmap()
                        _mealImages.value = _mealImages.value + mealImagesById
                    }
                }
            }
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