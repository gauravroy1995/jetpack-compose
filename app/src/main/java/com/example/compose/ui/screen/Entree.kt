package com.example.compose.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.compose.data.DataSource
import com.example.compose.ui.components.EachRow
import com.example.compose.ui.components.RowButtons
import com.example.compose.ui.viewmodel.VegetableViewModel

@Composable
fun Entree(viewModel: VegetableViewModel,onNextClick: () -> Unit,onCancelClick: () -> Unit){

    var selectedVegetable by rememberSaveable { mutableStateOf<Int?>(null) }



    Column {
        LazyColumn(contentPadding = PaddingValues( horizontal = 10.dp, vertical = 20.dp), modifier = Modifier.weight(1f) ){
           items(items=DataSource.entrees, itemContent = { item ->



               EachRow(id=item.id, title = item.name, price = item.price, benefits = item.benefits, selected = item.id == selectedVegetable, onClick = { id ->
                    selectedVegetable = id
                    viewModel.setEntreeId(id)
                   viewModel.setEntreePrice(item.price)
               },
                   modifier = Modifier.padding(vertical = 10.dp))

           })
        }



        RowButtons(onFirstClick =  onCancelClick , onSecondClick =  onNextClick , modifier =Modifier.fillMaxWidth().padding(16.dp).zIndex(2f) )


    }

}

