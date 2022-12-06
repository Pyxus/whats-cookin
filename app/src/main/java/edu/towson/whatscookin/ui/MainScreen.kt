package edu.towson.whatscookin.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.towson.whatscookin.ui.navigation.NavGraph
import edu.towson.whatscookin.ui.navigation.Screen

@Composable
fun MainScreen() {
    val nav = rememberNavController()
    val backStackEntry = nav.currentBackStackEntryAsState()
    val currentDestination = backStackEntry.value?.destination

    Scaffold(
     
        bottomBar = { BottomBar(nav) },
        floatingActionButton = {
            if (currentDestination?.route == Screen.Pantry.route) {
                FloatingActionButton(onClick = {
                    nav.navigate(Screen.AddToPantry.route)
                }) {
                    Icon(Icons.Filled.Add, contentDescription = "Add new pantry item")
                }
            }

        },
    ) { padding ->
        print(padding)
        NavGraph(nav)
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        elevation = 12.dp,
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Row() {
                Text(text = "Pantry", fontWeight = FontWeight.Bold, fontSize = 24.sp)
            }
            Row() {
                LazyRow(){
                    item { Text("test") }
                }
            }
        }
    }
}

@Composable
fun BottomBar(
    nav: NavHostController
) {
    val backStackEntry = nav.currentBackStackEntryAsState()
    val currentDestination = backStackEntry.value?.destination

    BottomNavigation(
        elevation = 12.dp
    ) {
        BottomNavigationItem(
            selected = currentDestination?.route == Screen.Pantry.route,
            label = { Text("Pantry") },
            onClick = {
                nav.navigate(Screen.Pantry.route) {
                    popUpTo(Screen.Pantry.route)
                }
            },
            icon = { Icon(Icons.Filled.Kitchen, contentDescription = "Navigate to pantry") }
        )

        BottomNavigationItem(
            selected = currentDestination?.route == Screen.Recipe.route,
            label = { Text("Recipes") },
            onClick = {
                nav.navigate(Screen.Recipe.route) {
                    popUpTo(Screen.Recipe.route)
                }
            },
            icon = { Icon(Icons.Filled.MenuBook, contentDescription = "Navigate to recipes") }
        )

        BottomNavigationItem(
            selected = currentDestination?.route == Screen.Tools.route,
            label = { Text("Tools") },
            onClick = {
                nav.navigate(Screen.Tools.route) {
                    popUpTo(Screen.Tools.route)
                }
            },
            icon = { Icon(Icons.Filled.Kitchen, contentDescription = "Navigate to tools") }
        )
    }
}