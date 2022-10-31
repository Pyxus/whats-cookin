package edu.towson.whatscookin.ui.screens.pantry.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun PantryRowItem(
    ingredientName: String,
    ingredientCount: Int,
){
    Card(
        shape = RoundedCornerShape(5.dp),
        elevation = 16.dp,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 5.dp, bottom = 10.dp)
            .fillMaxWidth()
            .clickable {

            }
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Row() {
                Column(modifier = Modifier.padding(end = 10.dp)) {
                    // TODO: Place holder - Some food icon could go here
                    Icon(Icons.Filled.Star, contentDescription = null)
                }
                Column(modifier = Modifier.padding(end = 5.dp)) {
                    Text(ingredientName, fontSize = 20.sp, fontWeight = FontWeight.Medium)
                }
                Column() {
                    Text("x${ingredientCount}")
                }
            }
            Row() {
                Text("Expiring in 5 weeks")
            }
        }

    }
}