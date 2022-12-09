package edu.towson.whatscookin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.towson.whatscookin.ui.screens.addtopantry.AddToPantry
import edu.towson.whatscookin.ui.screens.home.HomeScreen
import edu.towson.whatscookin.ui.screens.pantry.PantryScreen
import edu.towson.whatscookin.ui.screens.recipedetails.RecipeDetails
import edu.towson.whatscookin.ui.screens.recipie.RecipeScreen
import edu.towson.whatscookin.ui.screens.recipie.RecipeScreenViewModel

@Composable
fun NavGraph(
    nav: NavHostController = rememberNavController()
){
    val recipeScreenViewModel = viewModel<RecipeScreenViewModel>()

    NavHost(navController = nav, startDestination = Screen.Pantry.route){
        composable(Screen.Pantry.route){
            PantryScreen()
        }

        composable(Screen.AddToPantry.route){
            AddToPantry()
        }

        composable(Screen.Recipe.route){
            recipeScreenViewModel.updateMeals()
            RecipeScreen(
                vm = recipeScreenViewModel,
                onNavigateToRecipeDetails = {nav.navigate(Screen.RecipeDetails.route)}
            )
        }

        composable(Screen.RecipeDetails.route){
            RecipeDetails(
                vm = recipeScreenViewModel
            )
        }

        composable(Screen.Tools.route){
        }
    }
}