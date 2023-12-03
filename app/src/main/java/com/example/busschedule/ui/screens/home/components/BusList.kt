package com.example.busschedule.ui.screens.home.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.busschedule.R
import com.example.busschedule.data.BusEntity
import com.example.busschedule.data.BusSchedule
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun BusCard(schedule: BusEntity,onBusClick: (Int) -> Unit = {}) {

    val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    val currentTime = dateFormat.format(schedule.arrivalTimeInMillis)
    val busId = schedule.id
    

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(16.dp).clickable {
                onBusClick(busId)
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = schedule.stopName,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = currentTime.toString(),
            fontWeight = FontWeight.Bold
        )
    }

}