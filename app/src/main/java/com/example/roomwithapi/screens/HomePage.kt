package com.example.roomwithapi.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.roomwithapi.components.TextInputField
import com.example.roomwithapi.database.BusEntity
import com.example.roomwithapi.database.FavoritesEntity
import com.example.roomwithapi.viewmodel.AppViewModelProvider
import com.example.roomwithapi.viewmodel.FavoriteViewModel
import com.example.roomwithapi.viewmodel.HomeViewModel
import com.example.roomwithapi.viewmodel.ShareViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    navController: NavController,
    sharedViewModel: ShareViewModel,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    favoritesViewModel: FavoriteViewModel = viewModel(factory = AppViewModelProvider.Factory3)
) {
    val busUiState by viewModel.homeUiState.collectAsState()

    val favoritesState by favoritesViewModel.favItems.collectAsState()

    val coroutineScope = rememberCoroutineScope()


    val busScheduleList = busUiState.itemList

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Flights") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onSecondary
                ),

                )
        }
    ) {

        LaunchedEffect(viewModel.searchText) {


            /**
             * Delaying the search by 1 second basically debounce
             */
            delay(1000)

            viewModel.performSearch()


        }

        fun onDepartClick(busEntity: BusEntity) {
            sharedViewModel.setNewSelectedDeparture(busEntity)
            navController.navigate(BusScheduleScreens.Details.name)
        }


        @Composable
        fun renderBody() {
            if (viewModel.searchText != "") {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(
                        items = viewModel.searchedList,
                        key = { flight -> flight.id }) { busSchedule ->
                        EachCard(flightDetails = busSchedule, onClick = { it -> onDepartClick(it) })
                    }
                }
            } else {
                Text(text = "Favorites", modifier = Modifier.padding(8.dp))
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(
                        items = favoritesState,
                        key = { flight -> flight.id }) { busSchedule ->
                        FavoriteCard(flightDetails = busSchedule, onDeleteClick = {
                            coroutineScope.launch {
                                favoritesViewModel.deleteData(busSchedule)
                            }

                        })
                    }
                }
            }
        }





        Column(modifier = Modifier.padding(it)) {
            TextInputField(
                currentText = viewModel.searchText,
                onChangeText = {
                    viewModel.changeSearchText(it)


                    viewModel.saveSearchInDs(it)
                })

            renderBody()

        }

    }
}


@Composable
fun FavoriteCard(flightDetails: FavoritesEntity, onDeleteClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { /* Handle card click if needed */ },

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Display departure and destination codes
            Column {
                Text(text = "Departure: ${flightDetails.departureCode}")
                Text(text = "Destination: ${flightDetails.destinationCode}")
            }

            // Add a delete icon or text
            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}


@Composable
fun EachCard(flightDetails: BusEntity, onClick: (BusEntity) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick(flightDetails) },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = flightDetails.iataCode,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = flightDetails.name,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    }
}
