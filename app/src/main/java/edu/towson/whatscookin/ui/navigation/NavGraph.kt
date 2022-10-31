package edu.towson.whatscookin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.towson.whatscookin.ui.screens.pantry.PantryScreen

@Composable
fun NavGraph(
    navController: NavHostController,
){
    NavHost(
        navController = navController,
        startDestination = Screen.Pantry.route
    ){
        composable(
            route = Screen.Pantry.route
        ){ PantryScreen()}
    }
}