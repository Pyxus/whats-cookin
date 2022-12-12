package edu.towson.whatscookin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.towson.whatscookin.ui.screens.addallscreen.AddAllScreen
import edu.towson.whatscookin.ui.screens.addtopantry.AddToPantry
import edu.towson.whatscookin.ui.screens.addtopantry.AddToPantryViewModel
import edu.towson.whatscookin.ui.screens.pantry.PantryScreen
import edu.towson.whatscookin.ui.screens.pantry.PantryScreenViewModel
import edu.towson.whatscookin.ui.screens.recipedetails.RecipeDetails
import edu.towson.whatscookin.ui.screens.recipe.RecipeScreen
import edu.towson.whatscookin.ui.screens.recipe.RecipeScreenViewModel
import edu.towson.whatscookin.ui.shared.viewmodel.ApplicationViewModel
import kotlinx.coroutines.launch

@Composable
fun NavGraph(
    nav: NavHostController = rememberNavController()
) {
    val applicationViewModel = viewModel<ApplicationViewModel>()
    val recipeScreenViewModel = viewModel<RecipeScreenViewModel>()
    val pantryScreenViewModel = viewModel<PantryScreenViewModel>()
    val addToPantryViewModel = viewModel<AddToPantryViewModel>()

    LaunchedEffect(key1 = true) {
        recipeScreenViewModel.viewModelScope.launch {
            recipeScreenViewModel.updateMeals(applicationViewModel.getIngredients())
        }
    }

    NavHost(navController = nav, startDestination = Screen.Pantry.route) {
        composable(Screen.Pantry.route) {
            PantryScreen(
                vm = pantryScreenViewModel,
                appVm = applicationViewModel,
                onNavigateToAddPantry = { nav.navigate(Screen.AddToPantry.route) },
                onIngredientsDeleted = {
                    recipeScreenViewModel.viewModelScope.launch {
                        recipeScreenViewModel.updateMeals(applicationViewModel.getIngredients())
                    }
                }
            )
        }

        composable(Screen.AddToPantry.route) {
            AddToPantry(
                vm = addToPantryViewModel,
                onAddIngredientsClicked = {
                    addToPantryViewModel.prepSelectedIngredientsStorage()
                    nav.navigate(Screen.AddAllScreen.route)
                })
        }

        composable(Screen.Recipe.route) {
            RecipeScreen(
                vm = recipeScreenViewModel,
                onNavigateToRecipeDetails = { nav.navigate(Screen.RecipeDetails.route) }
            )
        }

        composable(Screen.RecipeDetails.route) {
            RecipeDetails(
                vm = recipeScreenViewModel
            )
        }

        composable(Screen.Tools.route) {
        }

        composable(Screen.AddAllScreen.route) {
            AddAllScreen(
                vm = addToPantryViewModel,
                appVm = applicationViewModel,
                onIngredientsAdded = {
                    recipeScreenViewModel.viewModelScope.launch {
                        recipeScreenViewModel.updateMeals(applicationViewModel.getIngredients())
                    }
                    nav.clearBackStack(Screen.Pantry.route)
                })
        }

    }
}