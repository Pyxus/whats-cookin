package edu.towson.whatscookin.ui.screens.pantry

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PantryScreen() {
    val vm = viewModel<PantryScreenViewModel>()

    Scaffold(
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(25.dp)
        ) {
            Header(vm)
            Row() {
                PantryList()
            }
        }
    }

}

@Composable
fun Header(
    vm: PantryScreenViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        SearchBar()
    }
    LazyRow() {
        item {
            StorageFilterItem(
                text = "All",
                isSelected = vm.selectedFilter == PantryScreenViewModel.StorageFilter.ALL,
                onClick = { vm.selectedFilter = PantryScreenViewModel.StorageFilter.ALL }
            )
        }

        item {
            StorageFilterItem(
                text = "Pantry",
                isSelected = vm.selectedFilter == PantryScreenViewModel.StorageFilter.PANTRY,
                onClick = { vm.selectedFilter = PantryScreenViewModel.StorageFilter.PANTRY }
            )
        }

        item {
            StorageFilterItem(
                text = "Fridge",
                isSelected = vm.selectedFilter == PantryScreenViewModel.StorageFilter.FRIDGE,
                onClick = { vm.selectedFilter = PantryScreenViewModel.StorageFilter.FRIDGE }
            )
        }
        item {
            StorageFilterItem(
                text = "Freezer",
                isSelected = vm.selectedFilter == PantryScreenViewModel.StorageFilter.FREEZER,
                onClick = { vm.selectedFilter = PantryScreenViewModel.StorageFilter.FREEZER }
            )
        }
    }
}

@Composable
private fun StorageFilterItem(
    text: String,
    onClick: () -> Unit,
    isSelected: Boolean = false,
    count: Int = 0
) {
    Column(modifier = Modifier.padding(horizontal = 5.dp, vertical = 15.dp)) {
        Row() {
            Button(
                border = BorderStroke(1.dp, Color.Gray),
                elevation = null,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (isSelected) MaterialTheme.colors.primary else Color.Transparent
                ),
                onClick = { onClick() }
            ) {
                Text(
                    text = "$text ($count)",
                    color = if (isSelected) Color.White else Color.Black
                )
            }
        }
    }
}


@Composable
private fun SearchBar() {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = {
            Text("Search your pantry")
        },
        trailingIcon = {
            Icon(
                Icons.Filled.Search,
                contentDescription = "Search pantry"
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun PantryList() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        item {
            PantryRowItem(ingredientName = "Test Food", ingredientCount = 5)
        }
    }
}

@Composable
fun PantryRowItem(
    ingredientName: String,
    ingredientCount: Int,
) {
    Card(
        shape = RoundedCornerShape(5.dp),
        elevation = 16.dp,
        modifier = Modifier
            //.padding(start = 16.dp, end = 16.dp, top = 5.dp, bottom = 10.dp)
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
                    Icon(Icons.Filled.Fastfood, contentDescription = null)
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
fun Preview() {
    PantryScreen()
}