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
import edu.towson.whatscookin.model.Ingredient
import edu.towson.whatscookin.model.StoredIngredient
import edu.towson.whatscookin.ui.screens.pantry.PantryScreenViewModel
import edu.towson.whatscookin.ui.shared.compose.SearchBar


@Composable
fun AddToPantry(){
    val vm = viewModel<AddToPantryViewModel>()
    Column {
        Row(){
            SearchBar(modifier = Modifier.fillMaxWidth(), placeholderText = "Type ingredient name to search")
        }
        Row(){
            LazyColumn(){
                items (vm.allIngredients.value){
                    ingredient -> IngredientRow(ingredient = ingredient)
                }
            }
        }
    }
}

@Composable
fun IngredientRow(ingredient: Ingredient){
    Card(
        shape = RoundedCornerShape(5.dp),
        elevation = 1.dp,
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(
                start = 2.dp,
                end = 2.dp,
                top = 2.dp,
                bottom = 2.dp
            )
            .fillMaxWidth()
            .clickable {

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
                Text(text = ingredient.name, color = MaterialTheme.colors.background)
            }
        }
    }
}

@Composable
fun AddRow(ingredient: Ingredient){
    Column(modifier = Modifier) {

    }
}

// temporarily changed for testing, was < ingredientImage: ImageBitmap? >
@Composable
fun IngredientCard(ingredientImage: String, ingredientName: String, onNavigateToPantry: () -> Unit) {

    // User input to remember
    val amount = remember { mutableStateOf("") }
    val expirationDate = remember { mutableStateOf("") }

    // var to hold a new Ingredient to store
    var ingredientToAdd = StoredIngredient(0, "", 0, "", "", "")


    Card(
        shape = RoundedCornerShape(5.dp),
        elevation = 1.dp,
        backgroundColor = Color.Gray,
        modifier = Modifier
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 8.dp,
                bottom = 8.dp
            )
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            // Image
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Filled.AddCircle,
                    "placeholder_forRecipeImage",
                    modifier = Modifier.size(48.dp),
                    tint = Color.Black
                )
            }

            // Ingredient name
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = ingredientName,
                    fontSize = 24.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold)
            }

            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column(
                ) {

                    // Text Field to enter amount
                    Text(text = (stringResource(R.string.enter_amount)),
                        fontSize = 12.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    OutlinedTextField(
                        value = amount.value,
                        onValueChange = { newAmount: String ->
                            amount.value = newAmount
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = {
                            Text(stringResource(R.string.amount_placeholder))
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.White,
                            backgroundColor = Color.LightGray,
                            textColor = Color.DarkGray,
                            placeholderColor = Color.Gray
                        )
                    )
                    Text(text = "${amount.value} kg",
                        fontSize = 8.sp,
                        color = Color.Black)


                    // Text Field to enter Expiration Date
                    Text(
                        text = (stringResource(R.string.enter_exp_date)),
                        fontSize = 12.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    OutlinedTextField(
                        value = expirationDate.value,
                        onValueChange = { newDate: String ->
                            expirationDate.value = newDate
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = {
                            Text(stringResource(R.string.date_placeholder))
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.White,
                            backgroundColor = Color.LightGray,
                            textColor = Color.DarkGray,
                            placeholderColor = Color.Gray
                        )
                    )
                    Text(text = expirationDate.value,
                        fontSize = 8.sp,
                        color = Color.Black)

                    // Button to determine where ingredient is stored
                    Row(
                        modifier = Modifier
                            .padding(top = 24.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Column() {
                            Button(
                                onClick = {
//                                    ingredientToAdd = StoredIngredient(1,
//                                        ingredientName,
//                                        amount.toString().toInt(),
//                                        "2022-12-09",
//                                        expirationDate.toString(),
//                                        "pantry")
                                          },
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = Color.DarkGray
                                )

                            ) {
                                Text(
                                    text = (stringResource(R.string.place_pantry)),
                                    color = Color.White
                                )
                            }

                            Button(
                                onClick = {
//                                    ingredientToAdd = StoredIngredient(2,
//                                    ingredientName,
//                                    amount.toString().toInt(),
//                                    "2022-12-09",
//                                    expirationDate.toString(),
//                                    "fridge")
                                          },
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = Color.DarkGray
                                )

                            ) {
                                Text(
                                    text = (stringResource(R.string.place_fridge)),
                                    color = Color.White
                                )
                            }

                            Button(
                                onClick = {
//                                    ingredientToAdd = StoredIngredient(3,
//                                    ingredientName,
//                                    amount.toString().toInt(),
//                                    "2022-12-09",
//                                    expirationDate.toString(),
//                                    "freezer")
                                    },
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = Color.DarkGray
                                )

                            ) {
                                Text(
                                    text = (stringResource(R.string.place_freezer)),
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}