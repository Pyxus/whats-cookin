package edu.towson.whatscookin.ui.screens.addpantryitem

import android.graphics.Color.parseColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.whatscookin.ui.screens.home.HomeScreen






@Composable
fun AddPantryItemScreen(){

    Column() {
        Row(horizontalArrangement = Arrangement.Center) {
            Text(
                text="Add New Item",
                fontSize =45.sp
            )
        }

    }
    AddNewItem()
}










@Composable
private fun FoodItem(){
Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
verticalArrangement = Arrangement.Center
) {

    OutlinedTextField (value = "ITEM", onValueChange = {})
    OutlinedTextField (value = "DATE", onValueChange = {})
    OutlinedTextField (value = "QUANTITY", onValueChange = {})
    OutlinedTextField (value = "EXPIRATION", onValueChange = {})


//    var text by remember {
//        mutableStateOf ("Ex: Lettuce") }
//    TextField (value = text, onValueChange = {newText-> text = newText})

    }

}



//@Composable
//private fun DateAdded(){
//    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//
//        TextField (value = "Date Added", onValueChange = {})
//
//    }






    @Composable
private fun AddNewItem(){
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            //.background(color = "#2596be".color)

    ) {
        NewRow("Add New Item")
    }
}




@Composable
private fun NewRow(text: String){
    Row(){
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .background(color = "#2596be".color)
        ) {
            Text(
                text = text,
                fontSize = 35.sp,
            )
        }
    }
}






val String.color
get()= Color(parseColor(this))


@Composable
@Preview(showBackground = true)
fun Preview(){
    AddPantryItemScreen()
}