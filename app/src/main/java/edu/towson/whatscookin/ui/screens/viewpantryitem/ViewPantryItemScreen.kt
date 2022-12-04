package edu.towson.whatscookin.ui.screens.viewpantryitem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ViewPantryItemScreen(){

    var input by remember {mutableStateOf("Find Pantry Item")}

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color.Companion.LightGray)

    ){

        titleLine()


        Row(){
            OutlinedTextField(value = input, onValueChange = {newText ->
                input = newText
            })
        }
        
        
        LazyColumn(){
            item{
                Text(text = "Hello")
            }
            
        }

        
        
        
        
        
    }
    

}

@Composable
private fun titleLine (){
    Row(){
        Text("My Pantry Items",
            fontSize = 40.sp,
        fontWeight = FontWeight.Bold)
    }
}



@Preview(showBackground = true)
@Composable
fun Preview(){
    ViewPantryItemScreen()
}







