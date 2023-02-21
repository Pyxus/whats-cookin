package edu.towson.whatscookin

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import edu.towson.whatscookin.network.TheMealDB
import edu.towson.whatscookin.ui.BottomBar
import edu.towson.whatscookin.ui.MainScreen
import edu.towson.whatscookin.ui.navigation.NavGraph
import edu.towson.whatscookin.ui.theme.WhatsCookinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatsCookinTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val nav = rememberNavController()

                    Scaffold(
                        bottomBar = { BottomBar(nav) },
                    ) { padding ->
                        print(padding)
                        NavGraph(nav)
                    }
                }
            }
        }
    }
}