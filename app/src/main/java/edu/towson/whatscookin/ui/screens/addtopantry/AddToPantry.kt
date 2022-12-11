package edu.towson.whatscookin.ui.screens.addtopantry

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.towson.whatscookin.R
import edu.towson.whatscookin.ext.similarity
import edu.towson.whatscookin.model.Ingredient
import edu.towson.whatscookin.model.StoredIngredient
import edu.towson.whatscookin.ui.screens.pantry.PantryScreenViewModel
import edu.towson.whatscookin.ui.shared.compose.SearchBar


@Composable
fun AddToPantry() {
    val vm = viewModel<AddToPantryViewModel>()
    Column {
        Row() {
            SearchBar(
                modifier = Modifier.fillMaxWidth(),
                placeholderText = "Type ingredient name to search",
                value = vm.searchText.value,
                onValueChange = { text ->
                    vm.searchText.value = text
                }
            )
        }
        Row() {
            LazyColumn() {
                val searchText = vm.searchText.value

                if (searchText.isEmpty()) {
                    items(vm.allIngredients.value) { ingredient ->
                        IngredientRow(ingredient = ingredient, vm = vm)
                    }
                } else {
                    val filteredIngredients = vm.allIngredients.value.filter { ingredient ->
                        // The Sorensen-Dice algorithm used in similairty() is based off a statistic
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
                    color = MaterialTheme.colors.background
                )
            }
        }
    }
}


//// temporarily changed for testing, was < ingredientImage: ImageBitmap? >
//@Composable
//fun IngredientCard(
//    ingredientImage: String,
//    ingredientName: String,
//    onNavigateToPantry: () -> Unit
//) {
//
//    // User input to remember
//    val amount = remember { mutableStateOf("") }
//    val expirationDate = remember { mutableStateOf("") }
//
//    // var to hold a new Ingredient to store
//    var ingredientToAdd = StoredIngredient(0, "", 0, "", "", "")
//
//
//    Card(
//        shape = RoundedCornerShape(5.dp),
//        elevation = 1.dp,
//        backgroundColor = Color.Gray,
//        modifier = Modifier
//            .padding(
//                start = 16.dp,
//                end = 16.dp,
//                top = 8.dp,
//                bottom = 8.dp
//            )
//            .fillMaxWidth()
//    ) {
//
//    }
//}