package edu.towson.whatscookin

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import edu.towson.whatscookin.network.TheMealDB
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

// Specific Task
//TODO: Implement tools screen. Should just be a list of simple tools: Unit converter, timer, etc.
//      Don't put too much time into them.


// 5pt Requirements
//TODO: Custom app theme (not 100% sure what this means but we could tweak the Theme.kt file)
//TODO: Notifications are a requirement. 2 ideas: food expiring notifications and timer notifications.

// Rubric Requirements
//TODO: App is responsive (basically multithreading used where appropriate)
//TODO: UI is clean and effective (pad well, color well, etc.)
//TODO: App is easy to use
//TODO: App never crashes
//TODO: Navigation is effective (pop nav stack when needed)
//TODO: Code is clean and can be read quickly. Comments explain important parts.
//TODO: No commented-out code
//TODO: Readme explains the project clearly