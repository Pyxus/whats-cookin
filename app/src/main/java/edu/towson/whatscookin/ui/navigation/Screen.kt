package edu.towson.whatscookin.ui.navigation

sealed class Screen(val route: String){
    object Pantry: Screen(route = "pantry")
    object Main: Screen(route = "main")
    object Home: Screen(route = "home")
    object Recipe: Screen(route = "recipe")
    object Tools: Screen(route = "tools")
    object AddPantryItem: Screen(route = "add-pantry-item")
    object ViewPantryItem: Screen(route = "view-pantry-item")
}