package edu.towson.whatscookin.ui.navigation

import androidx.compose.runtime.Composable
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

@Composable
fun NavGraph(
    nav: NavHostController = rememberNavController()
){
    NavHost(navController = nav, startDestination = Screen.Pantry.route){
        composable(Screen.Pantry.route){
            PantryScreen()
        }

        composable(Screen.AddToPantry.route){
            AddToPantry()
        }

        composable(Screen.Recipe.route){
            RecipeScreen(
                onNavigateToRecipeDetails = {nav.navigate(Screen.RecipeDetails.route)}
            )
        }

        composable(Screen.RecipeDetails.route){
            RecipeDetails()
        }

        composable(Screen.Tools.route){
        }
    }
}