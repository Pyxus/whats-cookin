package edu.towson.whatscookin.data

import edu.towson.whatscookin.model.StoredIngredient

interface InterfaceStoredIngredientsDatabase {
    suspend fun addIngredient(ingredient: StoredIngredient)
    suspend fun deleteIngredient(index: Int)
    suspend fun getIngredients(): List<StoredIngredient>
}