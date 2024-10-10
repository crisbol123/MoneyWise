package edu.unicauca.moneywise.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoneyWiseViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is MoneyWise Fragment"
    }
    val text: LiveData<String> = _text
}