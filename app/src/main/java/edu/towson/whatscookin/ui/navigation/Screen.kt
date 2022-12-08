package edu.towson.whatscookin.ui.navigation

sealed class Screen(val route: String){
    object Pantry: Screen(route = "pantry")
    object Main: Screen(route = "main")
    object Home: Screen(route = "home")
    object Recipe: Screen(route = "recipe")
    object Tools: Screen(route = "tools")
    object AddToPantry: Screen(route = "add-to-pantry")
    object ViewPantryItem: Screen(route = "view-pantry-item")
    object RecipeDetails: Screen(route = "recipe-details")
}