package edu.towson.whatscookin.model

import java.util.*

data class StoredIngredient(
    val ingredient: Ingredient,
    val count: Int,
    val dateAdded: Date,
    val expirationDate: Date,
)
