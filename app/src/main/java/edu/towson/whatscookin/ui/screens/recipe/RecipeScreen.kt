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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.whatscookin.R
import edu.towson.whatscookin.model.Meal

@Composable
fun RecipeScreen(vm: RecipeScreenViewModel, onNavigateToRecipeDetails: () -> Unit) {
    if (vm.mealSearchProgress.value.isSearchFinished) {
        if (vm.meals.value.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    start = 0.dp,
                    end = 0.dp,
                    top = 0.dp,
                    bottom = 128.dp
                ),
            ) {
                items(vm.meals.value) { meal ->
                    MealCard(
                        mealImage = vm.mealImages.value[meal.idMeal],
                        mealName = meal.name,
                        ingredientCount = "${"(${meal.ingredientsInPossession} / ${meal.measureByIngredient.keys.size}"})",
                        onNavigateToRecipeDetails = {
                            vm.selectedMeal = meal
                            onNavigateToRecipeDetails()
                        }
                    )
                }
            }
        } else {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = stringResource(R.string.recipe_no_ingredients), fontSize = 24.sp)
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
                Text(text = stringResource(R.string.recipe_loading))
            }
        }
    }
}

@Composable
fun MealCard(mealImage: ImageBitmap?, mealName: String, ingredientCount: String, onNavigateToRecipeDetails: () -> Unit) {
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
            Row(
                modifier = Modifier.padding(5.dp)
            ){
                Text("$ingredientCount ingredients")
            }
        }
    }
}