package edu.towson.whatscookin.network

import android.util.Log
import com.google.gson.Gson
import edu.towson.whatscookin.model.Ingredient
import edu.towson.whatscookin.model.Meal
import edu.towson.whatscookin.network.themealdb.IngredientsSchema
import edu.towson.whatscookin.network.themealdb.MealSchema
import edu.towson.whatscookin.network.themealdb.MealsByIngredientSchema
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

    // Searches MealDB and returns a list of all meals with given name.
    // If name is an exact match API returns list with 1 element.
    // Otherwise it will return multiple near matches.
    suspend fun searchMeal(mealName: String): List<Meal> {
        return withContext(Dispatchers.IO) {
            val request = Request.Builder()
                .get()
                .url("$apiUrl/search.php?s=$mealName")
                .build()
            val response = client.newCall(request).execute()

            getMealsFromResponse(response.body)
        }
    }

    // Searches MealDB and returns a list of all meals with given ID.
    // For some reason the api does return a list but since IDs are unique it will only have 1 element.
    suspend fun searchMeal(mealId: Int): List<Meal> {
        return withContext(Dispatchers.IO){
            val request = Request.Builder()
                .get()
                .url("$apiUrl/lookup.php?i=$mealId")
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
            val responseBody = response.body

            if (responseBody != null)
            {
                val jsonString = responseBody.string()
                val gson = Gson()
                val meals = gson.fromJson(jsonString, MealsByIngredientSchema::class.java)
                val mealList = meals.meals.mapNotNull { meal ->
                    val searchList = searchMeal(meal.idMeal)
                    if (searchList.isNotEmpty()) {
                        searchList[0]
                    } else {
                        null
                    }
                }

                mealList
            } else{
                listOf()
            }
        }
    }

    // Returns a list of all ingredients available in MealDB
    suspend fun getAllIngredients(): List<Ingredient> {
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
                ingredientList
            }else{
                listOf()
            }
        }
    }

    private fun mealSchemaToMeal(mealSchema: MealSchema): Meal{
        return Meal(
            idMeal = mealSchema.idMeal,
            name = mealSchema.strMeal,
            category = mealSchema.strCategory,
            region = mealSchema.strArea,
            instructions = mealSchema.strInstructions,
            imageUrl = mealSchema.strMealThumb,
            measureByIngredient = mealSchema.measureByIngredientMap(),
            tags = mealSchema.tagsToList(),
            youtubeUrl = mealSchema.strYoutube,
            recipeSourceUrl = mealSchema.strSource,
            imageSourceUrl = mealSchema.strImageSource,
        )
    }

    private fun getMealsFromResponse(responseBody: ResponseBody?): List<Meal> {
        return if (responseBody != null) {
            val jsonString = responseBody.string()
            val gson = Gson()
            val meals = gson.fromJson(jsonString, MealsSchema::class.java)
            val mealList = meals.meals.map { mealSchema ->
                mealSchemaToMeal(mealSchema)
            }

            mealList
        } else {
            listOf()
        }
    }


}