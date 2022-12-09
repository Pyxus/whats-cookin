package edu.towson.whatscookin.ui.screens.addtopantry

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import edu.towson.whatscookin.model.Ingredient
import edu.towson.whatscookin.model.Meal
import edu.towson.whatscookin.network.TheMealDB
import java.sql.Date

class AddToPantryViewModel : ViewModel() {

    private val theMealDB = TheMealDB()

//    private var prvtAmount = MutableState<Int>
//    val amount: State<Int>
//
//    private var prvtExpDate = MutableState<String>
//    val expDate: State<String>



//    init {
//        prvtAmount = mutableStateOf(0)
//        amount = prvtAmount
//
//        prvtExpDate = mutableStateOf("")
//        expDate = prvtExpDate
//    }



//    fun setAmount(amount: Int){
//        prvtAmount.value = amount
//    }
//
//    fun setExpDate(expirDate: String){
//        prvtExpDate.value = expirDate
//    }

}