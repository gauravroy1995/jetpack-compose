package com.example.roomwithapi.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roomwithapi.viewmodel.AppViewModelProvider
import com.example.roomwithapi.viewmodel.ShareViewModel


enum class BusScheduleScreens {
    Home,
    Details
}

@Composable
fun NavScreen(
    viewModel: ShareViewModel = viewModel(factory = AppViewModelProvider.Factory2)
) {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = BusScheduleScreens.Home.name
    ) {
        composable(BusScheduleScreens.Home.name) {
            HomePage(navController = navController, sharedViewModel = viewModel)
        }
        composable(BusScheduleScreens.Details.name) {
            DetailsScreen(navController = navController,sharedViewModel = viewModel)
        }
    }
}