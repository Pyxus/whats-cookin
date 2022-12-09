package edu.towson.whatscookin.data

interface InterfaceStoredIngredientsDatabase {
    fun getAllIngredientsName(): List<String>
    fun getAllExpiredIngredients(): List<String>
    fun removeAllExpiredIngredients()
}