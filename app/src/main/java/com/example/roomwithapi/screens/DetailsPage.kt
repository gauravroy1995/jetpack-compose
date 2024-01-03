package com.example.roomwithapi.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roomwithapi.database.BusEntity
import com.example.roomwithapi.database.FavoritesEntity
import com.example.roomwithapi.viewmodel.AppViewModelProvider
import com.example.roomwithapi.viewmodel.FavoriteViewModel
import com.example.roomwithapi.viewmodel.ShareViewModel
import kotlinx.coroutines.launch

@Composable
fun DetailsScreen(navController: Any, sharedViewModel: ShareViewModel) {
    Log.d("DetailsScreen", "hahaha")

    val selectedDeparture = sharedViewModel.selectedDeparture ?: BusEntity(
        id = 0,
        passengers = 2,
        iataCode = "No Code",
        name = "No Name"
    )


    val listOfOtherAirports = sharedViewModel.filteredItems.collectAsState()


    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "All Routes", fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.padding(16.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = 20.dp)
        ) {
            items(
                items = listOfOtherAirports.value,
                key = { item -> item.id },

                ) {
                DetailsCard(departure = selectedDeparture, destination = it)
            }
        }
    }


}


@Composable
fun DetailsCard(
    departure: BusEntity,
    destination: BusEntity,
    viewModel: FavoriteViewModel = viewModel(factory = AppViewModelProvider.Factory3)
) {
    val coroutineScope = rememberCoroutineScope()

    fun addFavorite() {
        val id = departure.id * destination.id
        val favEntity = FavoritesEntity(
            id = id,
            destinationCode = destination.iataCode,
            departureCode = departure.iataCode
        )

        coroutineScope.launch {
            viewModel.insertData(favEntity)
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 18.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "DEPARTURE", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Text(
                        text = departure.iataCode,
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = departure.name)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "DESTINATION", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Text(
                        text = destination.iataCode,
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = destination.name)
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box(
                modifier = Modifier
                    .background(Color.Red, shape = CircleShape)
                    .clickable { addFavorite() }
                    .padding(16.dp)
            ) {
                Text(
                    text = "Add to Fav",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


