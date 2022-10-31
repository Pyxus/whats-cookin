package edu.towson.whatscookin.ui.screens.ingredientdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun IngredientDetails(
    ingredientName: String
){
    Column() {
        Row() {
            Text(ingredientName)
        }

        Row() {
            //TODO: Created composable to display date added, expiration, and count
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview(){
    IngredientDetails("Eggs")
}