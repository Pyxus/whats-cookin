package edu.towson.whatscookin.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material.icons.filled.LunchDining
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import edu.towson.whatscookin.ui.navigation.NavGraph

@Composable
fun MainScreen(){
    val nav = rememberNavController()
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomBar() }
    ) { _ ->
        NavGraph(nav)
    }
}

@Composable
fun TopBar(){
    TopAppBar(
        elevation = 16.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Text(text= "Pantry", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        }
    }
}

@Composable
fun BottomBar(){
      BottomNavigation() {
          BottomNavigationItem(
              selected = false,
              label = { Text("Pantry") },
              onClick = { /*TODO*/ },
              icon = { Icon(Icons.Filled.Kitchen, contentDescription = null) }
          )

          BottomNavigationItem(
              selected = false,
              label = { Text("Recipes") },
              onClick = { /*TODO*/ },
              icon = { Icon(Icons.Filled.MenuBook, contentDescription = null) }
          )

          BottomNavigationItem(
              selected = false,
              label = { Text("Tools") },
              onClick = { /*TODO*/ },
              icon = { Icon(Icons.Filled.Kitchen, contentDescription = null) }
          )
      }
}