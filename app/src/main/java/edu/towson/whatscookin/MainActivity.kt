package edu.towson.whatscookin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import edu.towson.whatscookin.ui.MainScreen
import edu.towson.whatscookin.ui.theme.WhatsCookinTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatsCookinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

//TODO: Setup SQL Database
//TODO: Cache all MealDB Ingredients with SQLite.
//TODO: Implement "add to pantry" function
//TODO: Store user ingredients with SQLite