/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.busschedule.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.busschedule.R
import com.example.busschedule.ui.screens.add.AddScreen
import com.example.busschedule.ui.screens.details.BusDetailsScreen
import com.example.busschedule.ui.screens.home.HomeScreen

enum class BusScheduleScreens {
    FullSchedule,
    AddStop,
    BusDetails
}


@Composable
fun BusScheduleApp(
    viewModel: BusScheduleViewModel = viewModel(factory = BusScheduleViewModel.factory)
) {
    val navController = rememberNavController()
    val fullScheduleTitle = stringResource(R.string.full_schedule)
    var topAppBarTitle by remember { mutableStateOf(fullScheduleTitle) }
    val fullSchedule by viewModel.getFullSchedule().collectAsState(emptyList())
    val onBackHandler = {
        topAppBarTitle = fullScheduleTitle
        navController.navigateUp()
    }

    Scaffold(
        topBar = {
            BusScheduleTopAppBar(
                title = topAppBarTitle,
                canNavigateBack = navController.previousBackStackEntry != null,
                onBackClick = { onBackHandler() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BusScheduleScreens.FullSchedule.name
        ) {
            composable(BusScheduleScreens.FullSchedule.name) {

                HomeScreen(
                    contentPaddingValues = innerPadding,
                    onAddClick = {
                        navController.navigate(BusScheduleScreens.AddStop.name)
                    },
                    onBusClick = { busId ->
                        navController.navigate(
                            "${BusScheduleScreens.BusDetails.name}/$busId"
                        )
                        topAppBarTitle = busId.toString()
                    }
                )


//                FullScheduleScreen(
//                    busSchedules = fullSchedule,
//                    contentPadding = innerPadding,
//                    onScheduleClick = { busStopName ->
//                        navController.navigate(
//                            "${BusScheduleScreens.RouteSchedule.name}/$busStopName"
//                        )
//                        topAppBarTitle = busStopName
//                    }
//                )
            }

            composable(BusScheduleScreens.AddStop.name) {
                AddScreen(
                    navController = navController,
                    contentPaddingValues = innerPadding,
                )
            }

            composable(route = "${BusScheduleScreens.BusDetails.name}/{busId}",
                arguments = listOf(navArgument("busId") { type = NavType.IntType }
                )) { backStackEntry ->
                val busId = backStackEntry.arguments?.getInt("busId")
                    ?: error("busId cannot be null")

                BusDetailsScreen(busId = busId,paddingValues = innerPadding)
            }

        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusScheduleTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (canNavigateBack) {
        TopAppBar(
            title = { Text(title) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(
                            R.string.back
                        )
                    )
                }
            },
            modifier = modifier
        )
    } else {
        TopAppBar(
            title = { Text(title) },
            modifier = modifier
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun FullScheduleScreenPreview() {
//    BusScheduleTheme {
//        FullScheduleScreen(
//            busSchedules = List(3) { index ->
//                BusSchedule(
//                    index,
//                    "Main Street",
//                    111111
//                )
//            },
//            onScheduleClick = {}
//        )
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun RouteScheduleScreenPreview() {
//    BusScheduleTheme {
//        RouteScheduleScreen(
//            stopName = "Main Street",
//            busSchedules = List(3) { index ->
//                BusSchedule(
//                    index,
//                    "Main Street",
//                    111111
//                )
//            }
//        )
//    }
//}
