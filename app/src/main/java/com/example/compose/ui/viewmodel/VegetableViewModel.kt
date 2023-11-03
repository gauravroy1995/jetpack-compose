package com.example.compose.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.compose.ui.state.VegetableUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class VegetableViewModel:ViewModel() {
    /**
     * Vegetable state for this order
     */
    private val _uiState = MutableStateFlow(VegetableUiState())
    val uiState: StateFlow<VegetableUiState> = _uiState.asStateFlow()


    fun setVegetableId(vegetableId:Int){
        _uiState.value=_uiState.value.copy(selectedVegetableId = vegetableId)
    }

    fun setVegetablePrice(vegetablePrice:Double){
        _uiState.value=_uiState.value.copy(selectedVegetablePrice = vegetablePrice)
    }

    fun setEntreeId(entreeId:Int){
        _uiState.value=_uiState.value.copy(selectedEntreeId = entreeId)
    }

    fun setEntreePrice(entreePrice:Double){
        _uiState.value=_uiState.value.copy(selectedEntreePrice = entreePrice)
    }

    fun setAccompanyId(accompanyId:Int){
        _uiState.value=_uiState.value.copy(selectedAccompanyId = accompanyId)
    }

    fun setAccompanyPrice(accompanyPrice:Double){
        _uiState.value=_uiState.value.copy(selectedAccompanyPrice = accompanyPrice)
    }

    fun resetOrder(){
        _uiState.value=VegetableUiState()
    }




}