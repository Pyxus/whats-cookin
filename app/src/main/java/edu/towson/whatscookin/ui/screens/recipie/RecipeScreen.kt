package edu.towson.whatscookin.ui.screens.recipie

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RecipeScreen(){
    val desc = "this is a place holder"
    val extra = "this is just extra info"

    Column() {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(top = 75.dp, bottom = 75.dp)
                .fillMaxWidth()
        ) {
            Icon(Icons.Filled.AccountBox, "placeholder_forTopImage", modifier = Modifier.size(100.dp))

        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            LazyColumn() {
                items(10) { index ->
                    Card(
                        shape = RoundedCornerShape(5.dp),
                        elevation = 1.dp,
                        backgroundColor = Color.Transparent,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(32.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Filled.AddCircle,
                                "placeholder_forRecipeImage",
                                modifier = Modifier.size(48.dp)
                            )
                            Column(
                                modifier = Modifier.padding(start = 16.dp)
                            ) {
                                Text(text = "Recipe: $index")
                                Text(text = "Description: $desc", fontSize = 10.sp)
                                Text(text = "Extra Info: $extra", fontSize = 10.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}