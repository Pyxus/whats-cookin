package edu.towson.whatscookin.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(){
    Column() {
        Row(horizontalArrangement = Arrangement.Center) {
            Text(
                text = "What's Cookin'?",
                fontSize = 45.sp,
                modifier = Modifier.padding(30.dp)
            )
        }
        Row() {
            Menu()
        }
    }

}

@Composable
private fun Menu(){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()

        ) {
        MenuRow("Pantry")
        MenuRow("Recipes")
        MenuRow("Tools")
    }
}

@Composable
private fun MenuRow(text: String){
    Row(){
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = text,
                fontSize = 35.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview(){
    HomeScreen()
}