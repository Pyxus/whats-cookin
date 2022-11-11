package edu.towson.whatscookin.ui.screens.main

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import edu.towson.whatscookin.ui.navigation.NavGraph

@Composable
fun MainScreen(){
    val nav = rememberNavController()
    Scaffold {
        NavGraph(nav)
    }
}