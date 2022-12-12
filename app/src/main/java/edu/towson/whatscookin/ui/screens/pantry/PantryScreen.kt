package edu.towson.whatscookin.ui.screens.pantry

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.towson.whatscookin.db.entities.StoredIngredient
import edu.towson.whatscookin.ext.similarity
import edu.towson.whatscookin.ui.shared.compose.SearchBar
import edu.towson.whatscookin.ui.shared.viewmodel.ApplicationViewModel
import java.text.SimpleDateFormat

@Composable
fun PantryScreen(
    vm: PantryScreenViewModel,
    appVm: ApplicationViewModel,
    onNavigateToAddPantry: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar() {
                TopBar()
            }
        },
        bottomBar = {
            BottomAppBar() {
                // Intentionally empty. Only way I could get the FAB to move up
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colors.secondary,
                onClick = {
                    if (vm.isDeleteInitiated.value) {
                        vm.ingredientsSelectedForDeletion.value.forEach { storedIngredient ->
                            appVm.deleteIngredient(storedIngredient)
                            vm.clearDeleteQueue()
                        }

                    }
                    else{
                        onNavigateToAddPantry()
                    }
                }) {
                if (vm.isDeleteInitiated.value) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete selected pantry items"
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add new pantry item",
                    )
                }
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            Header(vm, appVm)
            Row() {
                PantryList(vm = vm, appVm = appVm)
            }
        }
    }

}

@Composable
private fun TopBar() {
    TopAppBar(
        elevation = 12.dp,
    ) {
        Row(
            modifier = Modifier.padding(10.dp)
        ) {
            Column() {
                Text(text = "Pantry", fontWeight = FontWeight.Bold, fontSize = 24.sp)
            }
        }
    }
}

@Composable
private fun Header(
    vm: PantryScreenViewModel,
    appVm: ApplicationViewModel,
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        SearchBar(
            value = vm.searchText.value,
            placeholderText = "Search your pantry",
            onValueChange = { newText ->
                vm.searchText.value = newText
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp, top = 15.dp, bottom = 5.dp)
        )
    }
    LazyRow(
        contentPadding = PaddingValues(horizontal = 19.dp)
    ) {
        item {
            StorageFilterItem(text = "All",
                isSelected = vm.selectedFilter == PantryScreenViewModel.StorageFilter.ALL,
                count = appVm.ingredient.value.size,
                onClick = { vm.selectedFilter = PantryScreenViewModel.StorageFilter.ALL })
        }

        item {
            StorageFilterItem(text = "Pantry",
                isSelected = vm.selectedFilter == PantryScreenViewModel.StorageFilter.PANTRY,
                count = appVm.ingredient.value.count { ingredient -> ingredient.storageLocation == StoredIngredient.Pantry },
                onClick = { vm.selectedFilter = PantryScreenViewModel.StorageFilter.PANTRY })
        }

        item {
            StorageFilterItem(text = "Fridge",
                isSelected = vm.selectedFilter == PantryScreenViewModel.StorageFilter.FRIDGE,
                count = appVm.ingredient.value.count { ingredient -> ingredient.storageLocation == StoredIngredient.Fridge },
                onClick = { vm.selectedFilter = PantryScreenViewModel.StorageFilter.FRIDGE })
        }

        item {
            StorageFilterItem(text = "Freezer",
                isSelected = vm.selectedFilter == PantryScreenViewModel.StorageFilter.FREEZER,
                count = appVm.ingredient.value.count { ingredient -> ingredient.storageLocation == StoredIngredient.Freezer },
                onClick = { vm.selectedFilter = PantryScreenViewModel.StorageFilter.FREEZER })
        }
    }
}

@Composable
private fun StorageFilterItem(
    text: String, onClick: () -> Unit, isSelected: Boolean = false, count: Int = 0
) {
    Column(
        modifier = Modifier.padding(horizontal = 5.dp, vertical = 15.dp)
    ) {
        Row() {
            Button(border = BorderStroke(1.dp, Color.Gray),
                elevation = null,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (isSelected) MaterialTheme.colors.primary else Color.Transparent
                ),
                onClick = { onClick() }) {
                Text(
                    text = "$text ($count)", color = if (isSelected) Color.White else Color.Black
                )
            }
        }
    }
}


@Composable
private fun PantryList(vm: PantryScreenViewModel, appVm: ApplicationViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp)
    ) {
        val ingredientsFilteredByLocation = appVm.ingredient.value.filter { storedIngredient ->
            when (vm.selectedFilter) {
                PantryScreenViewModel.StorageFilter.PANTRY -> {
                    storedIngredient.storageLocation == StoredIngredient.Pantry
                }
                PantryScreenViewModel.StorageFilter.FRIDGE -> {
                    storedIngredient.storageLocation == StoredIngredient.Fridge
                }
                PantryScreenViewModel.StorageFilter.FREEZER -> {
                    storedIngredient.storageLocation == StoredIngredient.Freezer
                }
                else -> true
            }
        }
        items(ingredientsFilteredByLocation.filter { storedIngredient ->
            val searchText = vm.searchText.value
            when (searchText.length) {
                0 -> true
                1 -> storedIngredient.name.startsWith(searchText, true)
                2 -> storedIngredient.name.similarity(searchText) >= .2f
                else -> storedIngredient.name.similarity(searchText) > .4f
            }
        }) { storedIngredient ->
            PantryRowItem(vm = vm, storedIngredient = storedIngredient)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PantryRowItem(
    vm: PantryScreenViewModel,
    storedIngredient: StoredIngredient,
) {
    Card(
        shape = RoundedCornerShape(2.dp), elevation = 16.dp, modifier = Modifier
            .padding(top = 5.dp, bottom = 10.dp)
            .fillMaxWidth()
            .combinedClickable(onClick = {
                vm.toggleDeletion(storedIngredient)
            }, onLongClick = {
                if (!vm.isDeleteInitiated.value) {
                    vm.enqueueDeletion(storedIngredient)
                }
                vm.isDeleteInitiated.value = true
            })
    ) {
        Row() {
            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Row() {
                    Column(modifier = Modifier.padding(end = 10.dp)) {
                        Icon(Icons.Filled.Fastfood, contentDescription = null)
                    }
                    Column(modifier = Modifier.padding(end = 5.dp)) {
                        Text(
                            storedIngredient.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Column() {
                        Text("x${storedIngredient.count}")
                    }
                }
                Row() {
                    val dateFormat = SimpleDateFormat("MM-dd-yyyy")
                    Text("Added ${dateFormat.format(storedIngredient.dateAdded)}")
                }
            }
            if (vm.isDeleteInitiated.value) {
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .fillMaxWidth()
                ) {
                    Checkbox(checked = vm.ingredientsSelectedForDeletion.value.contains(
                        storedIngredient
                    ),
                        onCheckedChange = { vm.toggleDeletion(storedIngredient) })
                }
            }
        }
    }
}