package edu.towson.whatscookin.data.implementation

import android.app.Application
import androidx.room.Room
import edu.towson.whatscookin.data.InterfaceStoredIngredientsDatabase
import edu.towson.whatscookin.data.StoredIngredientsDatabase


class StoredIngredientsDatabaseRepo(wc_app: Application) : InterfaceStoredIngredientsDatabase {

    private val sidb: StoredIngredientsDatabase

    init {
        sidb = Room.databaseBuilder(
            wc_app,
            StoredIngredientsDatabase::class.java,
            "ingredient_store"
        ).build()
    }

    override fun getAllIngredientsName(): List<String> {
        return sidb.storedIngredientsDao().getAllIngredientsName()
    }

    override fun getAllExpiredIngredients(): List<String> {
        return sidb.storedIngredientsDao().getAllExpiredIngredients()
    }

    override fun removeAllExpiredIngredients() {
//        sidb.storedIngredientsDao().removeExpiredIngredients()
    }

}