package com.example.compose.ui.state

data class VegetableUiState(
    val selectedVegetableId:Int?=null,
    val selectedVegetablePrice:Double=0.0,
    val selectedEntreeId:Int?=null,
    val selectedEntreePrice:Double=0.0,
    val selectedAccompanyId:Int?=null,
    val selectedAccompanyPrice:Double=0.0,
)
