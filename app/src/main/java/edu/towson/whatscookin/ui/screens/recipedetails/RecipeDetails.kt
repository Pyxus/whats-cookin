package edu.towson.whatscookin.ui.screens.recipedetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import edu.towson.whatscookin.ui.navigation.Screen
import edu.towson.whatscookin.ui.screens.recipie.RecipeScreenViewModel

@Composable
fun RecipeDetails(vm: RecipeScreenViewModel){
    val selectedMeal = vm.selectedMeal
    if (selectedMeal != null)
    {
        Column() {
            Row() {
                val mealImage = vm.mealImages.value[selectedMeal.idMeal]
                
                if (mealImage != null)
                {
                    Image(bitmap = mealImage, contentDescription = null)   
                }
            }
            Row() {
                Text(text = selectedMeal.name)
            }
            Row(){
                val mealInstructions = selectedMeal.instructions

                if (mealInstructions != null)
                {
                    LazyColumn(){
                        item{
                            Text(text = mealInstructions)
                        }
                    }
                }

            }
        }
    }
    else{
        Text(text = "Error failed to load meal.")
    }
}