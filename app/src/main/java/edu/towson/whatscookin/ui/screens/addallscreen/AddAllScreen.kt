package edu.towson.whatscookin.ui.screens.addallscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.whatscookin.R
import edu.towson.whatscookin.db.entities.StoredIngredient
import edu.towson.whatscookin.ui.screens.addtopantry.AddToPantryViewModel
import edu.towson.whatscookin.ui.shared.viewmodel.ApplicationViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddAllScreen(
    vm: AddToPantryViewModel,
    appVm: ApplicationViewModel,
    onIngredientsAdded: () -> Unit
) {
    val count = remember { mutableStateOf("") }

    Scaffold(
        backgroundColor = MaterialTheme.colors.primaryVariant,
        topBar = {
            AddAllTopBar()
        },
        bottomBar = {
            AddAllButton(vm = vm, appVm = appVm, onIngredientsAdded)
        }
    ) { _ ->
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(vm.ingredientsToStore.value) { storedIngredient ->
                AddScreenCard(
                    storedIngredient,
                    onCountChange = { count ->
                        vm.setIngredientToStoreCount(
                            storedIngredient,
                            maxOf(count, 1)
                        )
                    },
                    onStorageLocationClicked = { location ->
                        vm.setIngredientToStoreLocation(storedIngredient, location)
                    }
                )
            }
        }
    }
}

@Composable
fun AddAllTopBar() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.add_to_storage),
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.add_to_storage_instruct),
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
fun AddAllButton(
    vm: AddToPantryViewModel,
    appVm: ApplicationViewModel,
    onAddIngredientsClicked: () -> Unit
) {
    val checkAdd = remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        FloatingActionButton(
            onClick = {
                checkAdd.value = true
            },
            modifier = Modifier.padding(end = 10.dp, bottom = 66.dp),
            backgroundColor = MaterialTheme.colors.secondary,

            ) {
            Icon(
                Icons.Filled.Add,
                "addAll",
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colors.primaryVariant
            )
        }

        if (checkAdd.value) {
            AlertDialog(
                onDismissRequest = { checkAdd.value = false },
                title = {
                    Text(text = "Add All")
                },
                text = {
                    Text(text = "Are you sure you want to add all?")
                },
                confirmButton = {
                    Button(
                        onClick = {
                            checkAdd.value = false
                            onAddIngredientsClicked()
                        },
                        modifier = Modifier.padding(bottom = 8.dp, end = 8.dp)
                    ) {
                        Text(text = "Yes")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            checkAdd.value = false
                        },
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Text(text = "No")
                    }
                }
            )
        }
    }
}

@Composable
fun AddScreenCard(
    storedIngredient: StoredIngredient,
    onCountChange: (Int) -> Unit,
    onStorageLocationClicked: (String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 1.dp,
        modifier = Modifier
            .padding(
                start = 4.dp,
                end = 8.dp,
                top = 2.dp,
                bottom = 2.dp
            )
            .fillMaxWidth(),
        contentColor = MaterialTheme.colors.primary
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = storedIngredient.name,
                    modifier = Modifier.padding(top = 12.dp, start = 8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colors.primaryVariant
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Row(modifier = Modifier.padding(start = 2.dp, end = 2.dp)) {
                    Column(modifier = Modifier.padding(start = 2.dp, end = 2.dp)) {
                        OutlinedTextField(
                            value = storedIngredient.count.toString(),
                            placeholder = {
                                Text(
                                    text = stringResource(R.string.count),
                                    color = MaterialTheme.colors.background,
                                    fontSize = 12.sp
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Decimal
                            ),
                            onValueChange = { inputCount: String ->
                                try {
                                    onCountChange(inputCount.toInt())
                                } catch (e: Exception) {
                                    onCountChange(1)
                                }
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = MaterialTheme.colors.primary
                            ),
                            singleLine = true,
                            modifier = Modifier
                                .height(46.dp)
                                .width(46.dp),
                            textStyle = TextStyle.Default.copy(
                                fontSize = 12.sp,
                                color = MaterialTheme.colors.background
                            )
                        )
                    }
                    Column(modifier = Modifier.padding(start = 2.dp, end = 2.dp)) {
                        StorageLocationDropdown(onStorageLocationClicked = { location ->
                            onStorageLocationClicked(
                                location
                            )
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun StorageLocationDropdown(onStorageLocationClicked: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }

    val items = listOf(StoredIngredient.Pantry, StoredIngredient.Fridge, StoredIngredient.Freezer)

    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
    ) {
        Text(
            items[selectedIndex],
            modifier = Modifier
                .clickable(onClick = { expanded = true })
                .width(55.dp)
                .height(46.dp)
                .background(
                    MaterialTheme.colors.primary
                )
                .padding(top = 12.dp, start = 10.dp),
            fontSize = 12.sp,
            color = MaterialTheme.colors.background
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier.background(
                Color.Gray
            )
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                    onStorageLocationClicked(s)
                }) {
                    Text(text = s)
                }
            }
        }
    }
}