package edu.towson.whatscookin.ui.shared.model

import java.util.Date

data class Ingredient (
    val name: String,
    val count: Int,
    val dateAdded: Date,
    val expirationDate: Date,
    ){}