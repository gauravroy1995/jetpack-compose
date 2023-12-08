package com.example.compose.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.compose.database.EachBookEntity
import com.example.compose.network.EachBookClass

class SharedViewModel : ViewModel() {

    private val _currentSetBook: MutableState<EachBookEntity?> =
        mutableStateOf(null)

    val currentSetBook: State<EachBookEntity?> = _currentSetBook


    fun setData(data: EachBookEntity) {
        _currentSetBook.value = data
    }
}