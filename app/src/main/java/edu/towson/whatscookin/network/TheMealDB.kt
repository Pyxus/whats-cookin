package edu.towson.whatscookin.network

import com.google.gson.Gson
import edu.towson.whatscookin.model.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody

class TheMealDB {
    companion object{
        private val apiKey = "1"
        private val apiUrl = "www.themealdb.com/api/json/v1/$apiKey"

        suspend fun searchByName(mealName: String): List<Meal>{
            return withContext(Dispatchers.IO){
                val client = OkHttpClient()
                val request = Request.Builder()
                    .get()
                    .url("$apiUrl/search.php?s=$mealName")
                    .build()
                val response = client.newCall(request).execute()

                getMealsFromResponse(response.body)
            }
        }

        suspend fun searchByIngredient(ingredient: String): List<Meal>{
            return withContext(Dispatchers.IO){
                val client = OkHttpClient()
                val request = Request.Builder()
                    .get()
                    .url("$apiUrl/filter.php?i=$ingredient")
                    .build()
                val response = client.newCall(request).execute()

                getMealsFromResponse(response.body)
            }
        }

        suspend fun getAllIngredients(){
            return withContext(Dispatchers.IO){
                val client = OkHttpClient()
                val request = Request.Builder()
                    .get()
                    .url("$apiUrl/filter.php?i=list.php?i=list")
                    .build()
                val response = client.newCall(request).execute()

                getMealsFromResponse(response.body)
            }
        }
    }


}

private fun getMealsFromResponse(responseBody: ResponseBody?): List<Meal>{
    if (responseBody != null){
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
    } else{
        return listOf()
    }
}

private data class MealsSchema(
    val meals: List<MealSchema>
)

private data class MealSchema(
    val idMeal: Int,
    val strMeal: String,
    val strCategory: String,
    val strArea: String,
    val strInstructions: String,
    val strMealThumb: String,
    val strTags: String,
    val strYoutube: String,
    val strIngredient1: String,
    val strIngredient2: String,
    val strIngredient3: String,
    val strIngredient4: String,
    val strIngredient5: String,
    val strIngredient6: String,
    val strIngredient7: String,
    val strIngredient8: String,
    val strIngredient9: String,
    val strIngredient10: String,
    val strIngredient11: String,
    val strIngredient12: String,
    val strIngredient13: String,
    val strIngredient14: String,
    val strIngredient15: String,
    val strIngredient16: String,
    val strIngredient17: String,
    val strIngredient18: String,
    val strIngredient19: String,
    val strIngredient20: String,
    val strMeasure1: String,
    val strMeasure2: String,
    val strMeasure3: String,
    val strMeasure4: String,
    val strMeasure5: String,
    val strMeasure6: String,
    val strMeasure7: String,
    val strMeasure8: String,
    val strMeasure9: String,
    val strMeasure10: String,
    val strMeasure11: String,
    val strMeasure12: String,
    val strMeasure13: String,
    val strMeasure14: String,
    val strMeasure15: String,
    val strMeasure16: String,
    val strMeasure17: String,
    val strMeasure18: String,
    val strMeasure19: String,
    val strMeasure20: String,
    val strSource: String,
    val strImageSource: String,
    val strCreativeCommonsConfirmed: String,
    val dateModified: String,
){
    fun measureByIngredientMap(): Map<String, String>{
        return mapOf(
            strIngredient1 to strMeasure1,
            strIngredient2 to strMeasure2,
            strIngredient3 to strMeasure3,
            strIngredient4 to strMeasure4,
            strIngredient5 to strMeasure5,
            strIngredient6 to strMeasure6,
            strIngredient7 to strMeasure7,
            strIngredient8 to strMeasure8,
            strIngredient9 to strMeasure9,
            strIngredient10 to strMeasure10,
            strIngredient11 to strMeasure11,
            strIngredient12 to strMeasure12,
            strIngredient13 to strMeasure13,
            strIngredient14 to strMeasure14,
            strIngredient15 to strMeasure15,
            strIngredient16 to strMeasure16,
            strIngredient17 to strMeasure17,
            strIngredient18 to strMeasure18,
            strIngredient19 to strMeasure19,
            strIngredient20 to strMeasure20,
        )
    }

    fun tagsToList(): List<String>{
        return strTags.split(",")
    }
}
