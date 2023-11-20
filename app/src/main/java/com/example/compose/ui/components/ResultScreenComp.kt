package com.example.compose.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.compose.network.AmphibiansDataClass
import com.example.compose.ui.screens.AmphibianViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ResultScreen(photos: List<AmphibiansDataClass>,amphibianViewModel:AmphibianViewModel, modifier: Modifier = Modifier) {



    val pullRefreshState = rememberPullRefreshState(amphibianViewModel.isRefreshing(), { amphibianViewModel.refetchAmphibians() })


    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.pullRefresh(pullRefreshState)
    ) {

        LazyColumn(
            contentPadding = PaddingValues(4.dp)
        ) {
            items(photos.size) { index ->
                AmphibianCard(imageData = photos[index])
            }
        }

        PullRefreshIndicator(amphibianViewModel.isRefreshing(), pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}