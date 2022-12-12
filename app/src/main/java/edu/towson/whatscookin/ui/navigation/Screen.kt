package edu.towson.whatscookin.ui.navigation

sealed class Screen(val route: String){
    object Pantry: Screen(route = "pantry")
    object Recipe: Screen(route = "recipe")
    object Tools: Screen(route = "tools")
    object AddToPantry: Screen(route = "add-to-pantry")
    object RecipeDetails: Screen(route = "recipe-details")
    object AddAllScreen: Screen(route = "add-all-screen")
}