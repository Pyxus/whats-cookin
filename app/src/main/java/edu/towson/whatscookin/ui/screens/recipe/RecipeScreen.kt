package edu.towson.whatscookin.ui.screens.recipe

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp

//TODO: Display how many ingredients the users has compared to how many they need
//TODO: Navigate to instruction screen that displays instructions to make meal when clicked.
//      A lot of meals have youtube videos associated with them... Could look into video embedding.
//TODO: Last item in a long list is covered by the bottom nav bar

@Composable
fun RecipeScreen(vm: RecipeScreenViewModel, onNavigateToRecipeDetails: () -> Unit) {
    if (vm.mealSearchProgress.value.isSearchFinished) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 0.dp, end = 0.dp, top = 0.dp, bottom = 128.dp),
        ) {
            items(vm.meals.value) { meal ->
                MealCard(
                    mealImage = vm.mealImages.value[meal.idMeal],
                    mealName = meal.name,
                    onNavigateToRecipeDetails = {
                        vm.selectedMeal = meal
                        onNavigateToRecipeDetails()
                    }
                )
            }
        }
    } else {
        val progress: Float by animateFloatAsState(
            targetValue = vm.mealSearchProgress.value.getProgressFraction(),
            animationSpec = tween(1000),
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Row() {
                LinearProgressIndicator(progress = progress)
            }
            Row() {
                Text(text = "Figuring out what's on the menu!")
            }
        }
    }
}

@Composable
fun MealCard(mealImage: ImageBitmap?, mealName: String, onNavigateToRecipeDetails: () -> Unit) {
    Card(
        elevation = 15.dp,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable {
                onNavigateToRecipeDetails()
            }
    ) {
        Column() {
            Row() {
                if (mealImage != null) {
                    Image(bitmap = mealImage, contentDescription = null)
                } else {
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