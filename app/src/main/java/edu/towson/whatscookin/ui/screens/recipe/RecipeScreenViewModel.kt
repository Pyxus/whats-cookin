package edu.towson.whatscookin.ui.screens.recipe

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.towson.whatscookin.db.entities.StoredIngredient
import edu.towson.whatscookin.model.Ingredient
import edu.towson.whatscookin.model.Meal
import edu.towson.whatscookin.model.MealSearchProgress
import edu.towson.whatscookin.network.TheMealDB
import kotlinx.coroutines.Dispatchers
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

    private val _mealSearchProgress = mutableStateOf(MealSearchProgress())
    val mealSearchProgress: State<MealSearchProgress> = _mealSearchProgress

    var selectedMeal: Meal? = null

    fun updateMeals(ingredients: List<StoredIngredient>) {
        _mealSearchProgress.value =
            MealSearchProgress(totalIngredientCount = ingredients.size, isSearchFinished = false)

        viewModelScope.launch {
            _meals.value = withContext(Dispatchers.IO) {
                val client = OkHttpClient()
                val potentialMeals = mutableMapOf<Int, PotentialMeal>()
                var checkedIngredientCount = 0
                val maxMealSearch = 10


                ingredients.forEach { ingredient ->
                    var checkedMeals = 0
                    for(meal in _mealDb.searchByIngredient(ingredient.name)){
                        if (checkedMeals > maxMealSearch){
                            break
                        }
                        val pm = potentialMeals[meal.idMeal]
                        if (pm != null) {
                            pm.possessedIngredientCount++;
                        } else {
                            potentialMeals[meal.idMeal] = PotentialMeal(meal)
                        }
                        checkedMeals++
                    }

                    _mealSearchProgress.value = MealSearchProgress(
                        checkedIngredientCount = ++checkedIngredientCount,
                        totalIngredientCount = ingredients.size,
                        isSearchFinished = false
                    )
                }

                val pmValues = potentialMeals.values
                val filteredValues = pmValues.filter { pm -> pm.possessedIngredientCount != 0 }
                val sortedValues = filteredValues.sortedByDescending { pm ->
                    pm.possessedIngredientCount / pm.meal.measureByIngredient.keys.size.toFloat()
                }

                sortedValues.map { pm ->
                    pm.meal.ingredientsInPossession = pm.possessedIngredientCount
                    pm.meal
                }
            }

            _mealSearchProgress.value = MealSearchProgress()
            fetchMealImages()
        }
    }

    private suspend fun fetchImage(){
        val client = OkHttpClient()
        val request = Request.Builder()
            .get()
            .url("")
            .build()

        val response = client.newCall(request).execute()
        val responseBody = response.body

        if (responseBody != null){
            val bitmap = BitmapFactory.decodeStream(responseBody.byteStream())

        }
    }

    private suspend fun fetchMealImage(client: OkHttpClient, meal: Meal) {
        var bitmap: Bitmap? = null

        if (!_mealImages.value.containsKey(meal.idMeal)) {
            val request = Request.Builder()
                .get()
                .url("https://www.themealdb.com/api/json/v1/1/random.php")
                .build()
            val response = client.newCall(request).execute()
            val responseBody = response.body
            if (responseBody != null) {
                bitmap = BitmapFactory.decodeStream(responseBody.byteStream())
                if (bitmap != null) {
                    val mealImagesById = mutableMapOf<Int, ImageBitmap>()
                    mealImagesById[meal.idMeal] = bitmap.asImageBitmap()
                    _mealImages.value = _mealImages.value + mealImagesById
                }
            }
        }
    }

    private suspend fun fetchMealImages() {
        withContext(Dispatchers.IO) {
            val client = OkHttpClient()

            _meals.value.forEach { meal ->
                var bitmap: Bitmap? = null

                val request = Request.Builder()
                    .get()
                    .url(meal.imageUrl)
                    .build()
                val response = client.newCall(request).execute()
                val responseBody = response.body
                if (responseBody != null) {
                    bitmap = BitmapFactory.decodeStream(responseBody.byteStream())
                    if (bitmap != null) {
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