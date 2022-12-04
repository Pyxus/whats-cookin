package edu.towson.whatscookin.ui.screens.pantry

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.whatscookin.ui.screens.home.HomeScreen
//import androidx.compose.ui.graphics.*



@Composable
fun PantryScreen(){

    Column(
        horizontalAlignment = Alignment.Companion.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                   // .background(color.Blue)

    ){

        Row (){
            Text("Pantry", fontSize =45.sp)
        }






        Row(
            modifier = Modifier.fillMaxSize()
        ){
            Button(onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(60.dp)
                    .width(200.dp)

                ) {
                
                Text(text = "Add", fontSize = 29.sp)
                
            }
            
        }
    }
    
    
    
    
    
}

@Preview(showBackground = true)
@Composable
fun Preview(){
    PantryScreen()
}