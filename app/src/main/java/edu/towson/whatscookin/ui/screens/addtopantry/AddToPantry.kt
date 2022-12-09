package edu.towson.whatscookin.ui.screens.addtopantry

import android.graphics.Color.parseColor
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.whatscookin.ui.shared.compose.SearchBar


@Composable
fun AddToPantry(){
    Column {
        Row(){
            SearchBar(modifier = Modifier.fillMaxWidth(), placeholderText = "Type ingredient name to search")
        }
        Row(){

        }
    }
}

@Composable
fun IngredientCard(ingredientImage: ImageBitmap?, ingredientName: String, onNavigateToPantry: () -> Unit) {

    // User input to remember
    var amount = remember { mutableStateOf("0") }
    var expirationDate = remember { mutableStateOf("YYYY-MM-DD") }

    Card(
        shape = RoundedCornerShape(5.dp),
        elevation = 1.dp,
        backgroundColor = Color.LightGray,
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Filled.AddCircle,
                    "placeholder_forRecipeImage",
                    modifier = Modifier.size(48.dp)
                )
            }

            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = ingredientName, fontSize = 24.sp)
            }

            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column() {

                    Text(text = "Enter Amount in kg:", fontSize = 12.sp)
                    OutlinedTextField(
                        value = amount.value,
                        onValueChange = { newAmount: String ->
                            amount.value = newAmount
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.LightGray,
                            backgroundColor = Color.White
                        )
                    )

                    Text(text = "${amount.value} kg", fontSize = 8.sp)



                    Text(
                        text = "Enter Expiration Date (YYY-MM-DD) :",
                        fontSize = 12.sp
                    )
                    OutlinedTextField(
                        value = expirationDate.value,
                        onValueChange = { newDate: String ->
                            expirationDate.value = newDate
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.Gray,
                            backgroundColor = Color.White
                        )
                    )

                    Text(text = expirationDate.value, fontSize = 8.sp)

                    Row(
                        modifier = Modifier
                            .padding(top = 24.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Column() {
                            Button(
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = Color.DarkGray
                                )

                            ) {
                                Text(
                                    text = " Place in Pantry ",
                                    color = Color.White
                                )
                            }

                            Button(
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = Color.DarkGray
                                )

                            ) {
                                Text(
                                    text = " Place in Fridge ",
                                    color = Color.White
                                )
                            }

                            Button(
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = Color.DarkGray
                                )

                            ) {
                                Text(
                                    text = "Place in Freezer",
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