package com.example.compose.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.network.EachBookClass
import com.example.compose.ui.viewmodels.AmphibianViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ResultScreen(photos: List<EachBookClass>, amphibianViewModel: AmphibianViewModel, navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {



    val pullRefreshState = rememberPullRefreshState(amphibianViewModel.isRefreshing(), { amphibianViewModel.refetchAmphibians() })


    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.pullRefresh(pullRefreshState)
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 0.dp, end = 0.dp)
        ) {
            items(photos.size) { index ->
                AmphibianCard(imageData = photos[index],navController = navController)
            }
        }

        PullRefreshIndicator(amphibianViewModel.isRefreshing(), pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}