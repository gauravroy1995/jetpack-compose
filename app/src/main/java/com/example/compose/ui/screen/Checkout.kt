package com.example.compose.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment


import androidx.compose.ui.Modifier
import com.example.compose.data.DataSource
import com.example.compose.ui.theme.CabinBoldFont
import com.example.compose.ui.viewmodel.VegetableViewModel

@Composable
fun TextSpaceRow(title: String, value: String, modifier: Modifier = Modifier){
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween , verticalAlignment = Alignment.CenterVertically ){
        Text(text = title, fontFamily = CabinBoldFont)
        Text(text = value)
    }
}

@Composable
fun Checkout(viewModel: VegetableViewModel){


    val vegId = viewModel.uiState.collectAsState().value.selectedVegetableId
    val entreeId = viewModel.uiState.collectAsState().value.selectedEntreeId
    val accompanyId = viewModel.uiState.collectAsState().value.selectedAccompanyId

    val vegetableObj = DataSource.vegetables.find { it.id == vegId }
    val entreeObj = DataSource.entrees.find { it.id == entreeId }
    val accompanyObj = DataSource.accompanies.find { it.id == accompanyId }


    Column(modifier = Modifier.fillMaxSize()){
        Text(text = "Order Summary", fontFamily = CabinBoldFont, modifier = Modifier.align(Alignment.Start))
        if (vegetableObj != null) {
            TextSpaceRow(title=vegetableObj.name, value = vegetableObj.price.toString())
        }

        if(entreeObj != null){
            TextSpaceRow(title=entreeObj.name, value = entreeObj.price.toString())
        }

        if(accompanyObj != null){
            TextSpaceRow(title=accompanyObj.name, value = accompanyObj.price.toString())
        }
    }


}