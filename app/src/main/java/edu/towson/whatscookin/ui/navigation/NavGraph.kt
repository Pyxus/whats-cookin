package edu.towson.whatscookin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.towson.whatscookin.db.entities.StoredIngredient
import edu.towson.whatscookin.ui.screens.addallscreen.AddScreen
import edu.towson.whatscookin.ui.screens.addtopantry.AddToPantry
import edu.towson.whatscookin.ui.screens.pantry.PantryScreen
import edu.towson.whatscookin.ui.screens.pantry.PantryScreenViewModel
import edu.towson.whatscookin.ui.screens.recipedetails.RecipeDetails
import edu.towson.whatscookin.ui.screens.recipie.RecipeScreen
import edu.towson.whatscookin.ui.screens.recipie.RecipeScreenViewModel
import edu.towson.whatscookin.ui.shared.viewmodel.ApplicationViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun NavGraph(
    nav: NavHostController = rememberNavController()
) {
    val applicationViewModel = viewModel<ApplicationViewModel>()
    val recipeScreenViewModel = viewModel<RecipeScreenViewModel>()
    val pantryScreenViewModel = viewModel<PantryScreenViewModel>()

    NavHost(navController = nav, startDestination = Screen.Pantry.route) {
        composable(Screen.Pantry.route) {
            PantryScreen(
                vm = pantryScreenViewModel,
                appVm = applicationViewModel
            )
        }

        composable(Screen.AddToPantry.route) {
            //vm = AddToPantryViewModel,
            AddToPantry(onAddIngredientsClicked = {
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
            AddScreen()
        }

    }
}