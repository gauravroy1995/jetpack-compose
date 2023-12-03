package com.example.busschedule.ui.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.busschedule.R
import com.example.busschedule.data.BusSchedule
import com.example.busschedule.ui.screens.home.components.BusCard
import com.example.busschedule.ui.viewmodel.AppViewModelProvider
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    contentPaddingValues: PaddingValues = PaddingValues(),
    onAddClick: () -> Unit = {},
    onBusClick: (Int) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val busUiState by viewModel.homeUiState.collectAsState()

    val busScheduleList = busUiState.itemList

    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPaddingValues)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.stop_name),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(id = R.string.arrival_time),
                    fontWeight = FontWeight.Bold
                )

            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(
                    items = busScheduleList,
                    key = { busSchedule -> busSchedule.id }
                ) { schedule ->
                    BusCard(schedule = schedule,onBusClick=onBusClick)
                }
            }

        }

        FloatingActionButton(
            onClick = {
                    onAddClick()
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(horizontal = 16.dp, vertical = 48.dp)
        ) {
            // Your fab content here, e.g., an Icon or Text
            Text("Add")
        }


    }


}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FabAnywhere(
    fabPosition: FabPosition,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Scaffold(
        floatingActionButtonPosition = fabPosition,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Log.d("TAG", "FabAnywhere: ")
                    onClick()

                },
                modifier = modifier,
                content = content
            )
        },
        modifier = modifier.background(Color.Red)
    ) {}
}