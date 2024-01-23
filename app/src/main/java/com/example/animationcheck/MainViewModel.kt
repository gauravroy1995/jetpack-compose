package com.example.animationcheck

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class MainViewModel:ViewModel() {

    private val _showAnimationState = MutableStateFlow<Boolean>(false)
    val showAnimationState = _showAnimationState.asStateFlow()


    fun toggleAnimationState(){
        Log.d("MainViewModel", "toggleAnimationState: ${_showAnimationState.value} ")
        _showAnimationState.value = !_showAnimationState.value
    }

}