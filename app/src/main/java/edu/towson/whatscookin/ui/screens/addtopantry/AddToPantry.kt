package edu.towson.whatscookin.ui.screens.addtopantry

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import edu.towson.whatscookin.R
import edu.towson.whatscookin.ext.similarity
import edu.towson.whatscookin.model.Ingredient
import edu.towson.whatscookin.ui.shared.compose.SearchBar


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddToPantry(vm: AddToPantryViewModel, onAddIngredientsClicked: () -> Unit) {
    Scaffold(
        bottomBar = {
            BottomAppBar() {
                // Intentionally empty. To move FAB up
            }
        },
        floatingActionButton = {
            if (vm.selectedIngredients.value.isNotEmpty()) {
                FloatingActionButton(onClick = {
                    onAddIngredientsClicked()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add new pantry item",
                    )
                }
            }
        }) { _ ->
        Column {
            Row() {
                SearchBar(
                    modifier = Modifier.fillMaxWidth(),
                    placeholderText = stringResource(R.string.enter_to_search),
                    value = vm.searchText.value,
                    onValueChange = { text ->
                        vm.searchText.value = text
                    }
                )
            }
            Row() {
                IngredientList(vm = vm)
            }
        }
    }


}

@Composable
fun IngredientList(vm: AddToPantryViewModel) {
    if (vm.allIngredients.value.isNotEmpty()){
        LazyColumn() {
            val searchText = vm.searchText.value

            if (searchText.isEmpty()) {
                items(vm.allIngredients.value) { ingredient ->
                    IngredientRow(ingredient = ingredient, vm = vm)
                }
            } else {
                val filteredIngredients = vm.allIngredients.value.filter { ingredient ->
                    // The Sorensen-Dice algorithm used in similarity() is based off a statistic
                    // So the more input there is, the more accurate it is.
                    when (searchText.length) {
                        1 -> ingredient.name.startsWith(searchText, true)
                        2 -> ingredient.name.similarity(searchText) >= .2f
                        else -> ingredient.name.similarity(searchText) > .4f
                    }
                }

                items(filteredIngredients.sortedByDescending { ingredient ->
                    ingredient.name.similarity(
                        searchText
                    )
                }) { ingredient ->
                    IngredientRow(ingredient = ingredient, vm = vm)
                }
            }
        }
    } else{
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Row {
                CircularProgressIndicator()
            }
            Row() {
                Text(text = "Downloading ingredient list...")
            }
        }
    }
    
}

@Composable
fun IngredientRow(ingredient: Ingredient, vm: AddToPantryViewModel) {
    Card(
        shape = RoundedCornerShape(5.dp),
        elevation = 1.dp,
        modifier = Modifier
            .padding(
                start = 2.dp,
                end = 2.dp,
                top = 2.dp,
                bottom = 2.dp
            )
            .fillMaxWidth()
            .selectable(
                enabled = true,
                selected = vm.selectedIngredients.value.contains(ingredient.id),
                onClick = { vm.toggleSelection(ingredient.id) }
            ),
        backgroundColor =
        if (vm.selectedIngredients.value.contains(ingredient.id)) {
            MaterialTheme.colors.primaryVariant
        } else {
            MaterialTheme.colors.primary
        }

    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = ingredient.name,
                    color = Color.White
                )
            }
        }
    }
}