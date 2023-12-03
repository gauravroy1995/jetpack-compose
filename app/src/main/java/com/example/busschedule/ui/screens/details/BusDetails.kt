package com.example.busschedule.ui.screens.details

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.busschedule.data.BusEntity
import com.example.busschedule.ui.screens.home.HomeViewModel
import com.example.busschedule.ui.viewmodel.AppViewModelProvider
import java.util.concurrent.TimeUnit

@Composable
fun BusDetailsScreen(busId:Int, paddingValues: PaddingValues, viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)) {


    val busItemState by viewModel.getItemById(busId).collectAsState(initial = null)
    Log.d("BusDetailsScreen", "BusDetailsScreen: ${busItemState.toString()}")
    Log.d("BusDetailsScreen", "BusDetailsScreen: $busId")

    val busId = busItemState?.id ?: 0;
    val stopName = busItemState?.stopName ?: "";
    val arrivalTimeInMillis = busItemState?.arrivalTimeInMillis ?: 0;

    val hours = TimeUnit.MILLISECONDS.toHours(arrivalTimeInMillis.toLong())
    val minutes = TimeUnit.MILLISECONDS.toMinutes(arrivalTimeInMillis.toLong()) % 60
    val seconds = TimeUnit.MILLISECONDS.toSeconds(arrivalTimeInMillis.toLong()) % 60
    val formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds)


    Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            // Displaying ID, Stop Name, and Arrival Time
            Text("ID: $busId")
            Text("Stop Name: $stopName", )
            Text("Arrival Time: $formattedTime")

            // Divider between items
            Divider(modifier = Modifier.padding(vertical = 8.dp))
        }


}