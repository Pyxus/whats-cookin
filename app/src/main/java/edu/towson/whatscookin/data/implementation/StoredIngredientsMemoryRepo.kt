package edu.towson.whatscookin.data.implementation

import edu.towson.whatscookin.data.InterfaceStoredIngredientsDatabase
import edu.towson.whatscookin.model.StoredIngredient
import kotlinx.coroutines.delay

class StoredIngredientsMemoryRepo(private var _ingredients: List<StoredIngredient>) : InterfaceStoredIngredientsDatabase {
    init {  }

    override suspend fun addIngredient(ingredient: StoredIngredient) {
        _ingredients = listOf(ingredient) + _ingredients
    }

    override suspend fun deleteIngredient(idx: Int) {
        delay(5000)
        _ingredients = _ingredients.subList(0, idx) + _ingredients.subList(idx + 1, _ingredients.size)
    }

    override suspend fun getIngredients(): List<StoredIngredient> {
        return _ingredients
    }
}