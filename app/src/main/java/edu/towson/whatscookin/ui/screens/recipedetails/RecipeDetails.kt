package edu.towson.whatscookin.ui.screens.recipedetails

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.whatscookin.ui.navigation.Screen
import edu.towson.whatscookin.ui.screens.recipie.RecipeScreenViewModel

@Composable
fun RecipeDetails(vm: RecipeScreenViewModel) {
    val selectedMeal = vm.selectedMeal
    if (selectedMeal != null) {
        LazyColumn(contentPadding = PaddingValues(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 64.dp)) {
            item {
                Column() {
                    Row() {
                        val mealImage = vm.mealImages.value[selectedMeal.idMeal]

                        if (mealImage != null) {
                            MealImage(mealImage, selectedMeal.name)
                        }
                    }

                    Row() {
                        IngredientList(selectedMeal.measureByIngredient)
                    }

                    Row() {
                        InstructionsView(selectedMeal.instructions)
                    }

                    val youtubeUrl = selectedMeal.youtubeUrl
                    if (youtubeUrl != null) {
                        Row(modifier = Modifier.padding(8.dp)) {
                            val context = LocalContext.current
                            val intent = remember {
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse(youtubeUrl)
                                )
                            }

                            Button(onClick = { context.startActivity(intent) }) {
                                Text("Youtube Tutorial")
                            }
                        }
                    }

                }
            }
        }
    } else {
        Text(text = "Error failed to load meal.")
    }
}

@Composable
private fun InstructionsView(instructions: String) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = instructions)
    }
}

@Composable
private fun IngredientList(measureByIngredient: Map<String, String>) {
    Column() {
        Row() {
            Card(
                elevation = 8.dp,
                modifier = Modifier.padding(8.dp)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Row() {
                        Text("Ingredients", fontWeight = FontWeight.Black)
                    }
                    measureByIngredient.keys.toList().forEach { ingredientName ->
                        Row() {
                            Column() {
                                measureByIngredient[ingredientName]?.let { measure ->
                                    Text(
                                        text = "$measure ",
                                        color = MaterialTheme.colors.primary,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                            Column() {
                                Text(ingredientName)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MealImage(mealImage: ImageBitmap, mealName: String) {
    Box(
        modifier = Modifier.background(Color.Red)
    ) {
        Image(
            bitmap = mealImage,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = mealName,
            color = Color.White,
            fontSize = 26.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        )
                    )
                )
                .padding(start = 4.dp, end = 8.dp, bottom = 12.dp, top = 32.dp)
        )
    }
}