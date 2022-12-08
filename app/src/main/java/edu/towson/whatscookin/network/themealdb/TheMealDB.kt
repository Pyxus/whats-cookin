package edu.towson.whatscookin.network

import com.google.gson.Gson
import edu.towson.whatscookin.model.Ingredient
import edu.towson.whatscookin.model.Meal
import edu.towson.whatscookin.network.themealdb.IngredientsSchema
import edu.towson.whatscookin.network.themealdb.MealsSchema
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody

class TheMealDB {
    private val apiKey = "1"
    private val apiUrl = "https://www.themealdb.com/api/json/v1/$apiKey"
    private val client = OkHttpClient()

    suspend fun searchByName(mealName: String): List<Meal> {
        return withContext(Dispatchers.IO) {
            val request = Request.Builder()
                .get()
                .url("$apiUrl/search.php?s=$mealName")
                .build()
            val response = client.newCall(request).execute()

            getMealsFromResponse(response.body)
        }
    }

    // Searched MealDB and returns a list of all meals featuring the given ingredient
    suspend fun searchByIngredient(ingredient: String): List<Meal> {
        return withContext(Dispatchers.IO) {
            val request = Request.Builder()
                .get()
                .url("$apiUrl/filter.php?i=$ingredient")
                .build()
            val response = client.newCall(request).execute()

            getMealsFromResponse(response.body)
        }
    }

    // Returns a list of all ingredients used in MealDB
    suspend fun getAllIngredients() {
        return withContext(Dispatchers.IO) {
            val request = Request.Builder()
                .get()
                .url("$apiUrl/list.php?i=list")
                .build()
            val response = client.newCall(request).execute()
            val responseBody = response.body
            if (responseBody != null) {
                val jsonString = responseBody.string()
                val gson = Gson()
                val ingredients = gson.fromJson(jsonString, IngredientsSchema::class.java)
                val ingredientList = ingredients.meals.map { ingredient ->
                    Ingredient(
                        name = ingredient.strIngredient,
                        description = ingredient.strDescription,
                        imageUrl = "https://themealdb.com/images/ingredients/${ingredient.strIngredient}.png",
                        id = ingredient.idIngredient,
                    )
                }
            }
        }
    }

    private fun getMealsFromResponse(responseBody: ResponseBody?): List<Meal> {
        if (responseBody != null) {
            val jsonString = responseBody.string()
            val gson = Gson()
            val meals = gson.fromJson(jsonString, MealsSchema::class.java)

            val mealList = meals.meals.map { meal ->
                Meal(
                    idMeal = meal.idMeal,
                    name = meal.strMeal,
                    category = meal.strCategory,
                    region = meal.strArea,
                    instructions = meal.strInstructions,
                    imageUrl = meal.strMealThumb,
                    measureByIngredient = meal.measureByIngredientMap(),
                    tags = meal.tagsToList(),
                    youtubeUrl = meal.strYoutube,
                    recipeSourceUrl = meal.strSource,
                    imageSourceUrl = meal.strImageSource,
                )
            }
            return mealList
        } else {
            return listOf()
        }
    }


}