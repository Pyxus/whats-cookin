package edu.towson.whatscookin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.towson.whatscookin.ui.screens.home.HomeScreen
import edu.towson.whatscookin.ui.screens.pantry.PantryScreen

@Composable
fun NavGraph(
    nav: NavHostController = rememberNavController()
){
    NavHost(navController = nav, startDestination = Screen.Pantry.route){
        composable(Screen.Pantry.route){
            PantryScreen()
        }
        composable(Screen.Recipe.route){
        }
        composable(Screen.Tools.route){
        }
    }
}