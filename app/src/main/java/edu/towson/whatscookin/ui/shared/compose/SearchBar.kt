package edu.towson.whatscookin.ui.shared.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchBar(modifier: Modifier = Modifier, placeholderText: String = "") {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = {
            Text(placeholderText)
        },
        trailingIcon = {
            Icon(
                Icons.Filled.Search,
                contentDescription = "Search pantry"
            )
        },
        modifier = modifier
    )
}