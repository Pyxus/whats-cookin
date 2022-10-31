package edu.towson.whatscookin.ui.screens.pantry

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.towson.whatscookin.ui.screens.pantry.compose.PantryRowItem

@Composable
fun PantryScreen(){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Add, contentDescription = "Add new pantry item")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) {
        Column {
            Row(modifier = Modifier
                .padding(24.dp)
            ){
                Header()
            }

            Row() {
                Body()
            }
        }
    }

}

@Composable
private fun Header(){
    Column() {
        SearchBar()
    }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(5.dp)
    ) {
        Icon(Icons.Filled.Notifications, contentDescription = null)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(5.dp)
    ) {
        Icon(Icons.Filled.Menu, contentDescription = null)
    }
}

@Composable
private fun SearchBar() {
    TextField(
        value = "",
        placeholder = { Text("Search Pantry") },
        onValueChange = {},
        //modifier = Modifier.fillMaxWidth(),
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search pantry")},
    )
}

@Composable
private fun Body(){
    Row() {
        LazyColumn(){
            item {
                for (i in 0..10)
                {
                    PantryRowItem("Eggs", 12)
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun Preview(){
    PantryScreen()
}