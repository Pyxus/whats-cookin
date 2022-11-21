package edu.towson.whatscookin.ui.screens.pantry

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantryScreen(){
    Scaffold(
        floatingActionButton = { FloatingActionButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.Add, contentDescription = "Add new pantry item")
        }},
        topBar = {
            TopAppBar() {
                Text(text = "Pantry", fontSize = 30.sp)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(30.dp)
        ) {
            Header()
            Row() {
                PantryList()
            }
        }
    }

}

@Composable
fun Header() {
    Row() {
        SearchBar()
    }
    LazyRow(){
        item {
            StorageFilterItem("All")
        }
        item {
            StorageFilterItem("Fridge")
        }
        item {
            StorageFilterItem("Freezer")
        }
        item {
            StorageFilterItem("Pantry")
        }
    }
}

@Composable
private fun StorageFilterItem(text: String){
    Column(modifier = Modifier.padding(5.dp)) {
        Row() {
            Text(text)
        }
    }
}

@Composable
private fun SearchBar(){
    TextField(
        value = "",
        onValueChange = {},
        placeholder = {Text("Search")}
    )
}

@Composable
private fun PantryList(){
    LazyColumn(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
    ){
        item{
            PantryRowItem("test", 9)
        }
        item{
            PantryRowItem("test", 9)
        }
    }
}

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

@Preview(showBackground = true)
@Composable
fun Preview(){
    PantryScreen()
}