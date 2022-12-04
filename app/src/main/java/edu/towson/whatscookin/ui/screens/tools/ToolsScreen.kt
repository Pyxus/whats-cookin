package edu.towson.whatscookin.ui.screens.tools

import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ToolsScreen(){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ){

        Row(
            horizontalArrangement = Arrangement.Center,


        ) {
            Text(
                text = "Tools",
                fontWeight = FontWeight.Bold,
                fontSize = 60.sp,
                color = Color.White,

                modifier = Modifier.padding(30.dp)
                    .background(Color.DarkGray)
            )
        }




        Row(
            verticalAlignment= Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,

            modifier = Modifier.padding(45.dp)

                .background(Color.Cyan)
        ){
            Button(onClick = { /*TODO*/ }) {
                Text("Converter")

            }



        }

        Row(
            verticalAlignment= Alignment.Bottom,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.padding(45.dp)
                .fillMaxSize()
                .background(Color.Red)
        ){
            Button(onClick = { /*TODO*/ }) {
                Text("Timer")

            }



        }

        Row(

            modifier = Modifier.padding(45.dp)
                .fillMaxWidth()
                .background(Color.DarkGray)
        ){}

        }


}

@Preview(showBackground = true)
@Composable
fun Preview(){
    ToolsScreen()}

