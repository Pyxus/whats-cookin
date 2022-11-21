package edu.towson.whatscookin.ui.screens.addpantryitem

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.whatscookin.ui.screens.home.HomeScreen
//import edu.towson.whatscookin.ui.screens.home.MenuRow

//import edu.towson.whatscookin.ui.screens.home.MenuRow





@Composable
fun AddPantryItemScreen(){

    Column() {
        Row(horizontalArrangement = Arrangement.Center) {
            Text(
                text="Add New Item",
                fontSize =45.sp
            )
        }

        FoodItem()

    }
    AddNewItem()
}










@Composable
private fun FoodItem(){
Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
verticalArrangement = Arrangement.Center
) {

    TextField (value = "Ex: Lettuce", onValueChange = {})


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
        ) {
            Text(
                text = text,
                fontSize = 35.sp,
            )
        }
    }
}









@Composable
@Preview(showBackground = true)
fun Preview(){
    AddPantryItemScreen()
}