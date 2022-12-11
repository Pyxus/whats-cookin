package edu.towson.whatscookin.data.implementation

import edu.towson.whatscookin.data.InterfaceStoredIngredientsDatabase
import edu.towson.whatscookin.model.StoredIngredient

class StoredIngredientsMemoryRepo(private var _ingredients: List<StoredIngredient>) : InterfaceStoredIngredientsDatabase {
    init {
    }

    override suspend fun getIngredients(): List<StoredIngredient> {
        return _ingredients
    }

    override suspend fun deleteIngredient(index: Int) {
        _ingredients = _ingredients.subList(0, index) + _ingredients.subList(index + 1, _ingredients.size)
    }

    override suspend fun addIngredient(ingredient: StoredIngredient) {
        _ingredients = listOf(ingredient) + _ingredients
    }

}