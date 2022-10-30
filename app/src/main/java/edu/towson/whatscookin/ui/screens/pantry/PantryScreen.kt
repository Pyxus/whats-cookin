package edu.towson.whatscookin.ui.screens.pantry

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PantryScreen(){
    Column{
        Row{
            TextField(
                value = "",
                placeholder = { Text("Search Pantry")},
                onValueChange = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview(){
    PantryScreen()
}