package edu.towson.whatscookin.ui.screens.recipie

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.towson.whatscookin.network.TheMealDB

//TODO: Display how many ingredients the users has compared to how many they need

@Composable
fun RecipeScreen() {
    val vm = viewModel<RecipeScreenViewModel>()
    val desc = "this is a place holder"
    val extra = "this is just extra info"

    // THIS IS JUST FOR TESTING.
    // Need to update on screen load on recomposition.
    vm.updateMeals()

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(vm.meals.value) { meal ->
            MealCard(mealImage = vm.mealImages.value[meal.idMeal], mealName = meal.name)
        }
    }
}

@Composable
fun MealCard(mealImage: ImageBitmap?, mealName: String) {
    Card(
        elevation = 15.dp,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable { }
    ) {
        Column() {
            Row() {
                if (mealImage != null){
                    Image(bitmap = mealImage, contentDescription = null)
                }else {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .requiredWidth(50.dp)
                            .requiredHeight(150.dp)
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
            Row(
                modifier = Modifier.padding(5.dp)
            ) {
                Text(mealName)
            }
        }
    }
}