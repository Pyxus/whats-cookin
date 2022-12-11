package edu.towson.whatscookin.ui.screens.addallscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddScreen() {
    val count = remember { mutableStateOf("") }

    Scaffold(
        backgroundColor = MaterialTheme.colors.primaryVariant,
        topBar = {
            AddAllTopBar()
        },
        bottomBar = {
            AddAllButton()
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item {
                AddScreenCard(count)
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
                text = "Add to Storage",
                fontSize = 24.sp,
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "(Choose ingredient count and location for each)",
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
fun AddAllButton() {
    val checkAdd = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        FloatingActionButton(
            onClick = {
                checkAdd.value = true
            },
            modifier = Modifier.padding(12.dp),
            backgroundColor = MaterialTheme.colors.primary,

            ) {
            Icon(
                Icons.Filled.Add,
                "addAll",
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colors.primaryVariant
            )
        }

        if (checkAdd.value){
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
fun AddScreenCard(count: MutableState<String>) {
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
        Column {
            Row {
                Text(
                    text = "ingredient.name",
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedTextField(
                    value = count.value,
                    placeholder = {
                        Text(
                            text = "Enter Count",
                            color = MaterialTheme.colors.background,
                            fontSize = 16.sp
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    onValueChange = { inputCount: String ->
                        count.value = inputCount
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.primary
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .height(52.dp)
                        .width(128.dp),
                    textStyle = TextStyle.Default.copy(
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.background
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 24.dp, end = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                StorageLocationDropdown()
            }
        }
    }
}

@Composable
fun StorageLocationDropdown() {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }

    val items = listOf("Pantry", "Fridge", "Freezer")

    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
    ) {
        Text(
            items[selectedIndex],
            modifier = Modifier
                .clickable(onClick = { expanded = true })
                .width(128.dp)
                .height(44.dp)
                .background(
                    MaterialTheme.colors.primary
                )
                .padding(top = 8.dp, start = 12.dp),
            fontSize = 20.sp,
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
                }) {
                    Text(text = s)
                }
            }
        }
    }
}