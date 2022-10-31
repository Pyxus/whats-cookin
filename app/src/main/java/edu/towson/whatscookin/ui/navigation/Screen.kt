package edu.towson.whatscookin.ui.navigation

sealed class Screen(val route: String){
    object Pantry: Screen(route = "pantry_screen")
}