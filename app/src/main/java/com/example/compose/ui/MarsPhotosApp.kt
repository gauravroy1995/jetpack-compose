/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.compose.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.ui.viewmodels.AmphibianViewModel
import com.example.compose.ui.screens.HomeScreen


@Composable
fun MarsPhotosApp(navController: NavHostController = rememberNavController()) {


    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val amphibianUiModel: AmphibianViewModel =
            viewModel(factory = AmphibianViewModel.Factory)
        HomeScreen(
            amphibianUiState = amphibianUiModel.marsUiState,
            amphibianViewModel = amphibianUiModel,
            navController=navController
        )
    }
}


@Composable
fun MarsTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = "Books",
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )
}
