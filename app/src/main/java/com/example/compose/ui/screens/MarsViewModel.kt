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
package com.example.compose.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.compose.MarsPhotosApplication
import com.example.compose.data.AmphibianPhotosRepository
import com.example.compose.network.AmphibiansDataClass
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface AmphibianUiState {
    data class Success(val amphibians: List<AmphibiansDataClass>) : AmphibianUiState
    object Error : AmphibianUiState
    object Loading : AmphibianUiState

    object Refreshing : AmphibianUiState
}


class AmphibianViewModel(private val amphibianPhotosRepository: AmphibianPhotosRepository) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var marsUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getAmphibians()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    fun getAmphibians() {
        viewModelScope.launch {
            marsUiState = try {
                val listResult = amphibianPhotosRepository.getAmphibians()
                Log.d("MarsViewModel", "getAmphibians: $listResult")
                AmphibianUiState.Success(listResult)
            } catch (e: IOException) {
                AmphibianUiState.Error
            }
        }
    }

    fun isRefreshing():Boolean{
        return when(marsUiState){
            is AmphibianUiState.Refreshing -> true
            else -> false
        }
    }

    fun refetchAmphibians() {
        viewModelScope.launch {
            marsUiState = AmphibianUiState.Refreshing
            marsUiState = try {
                val listResult = amphibianPhotosRepository.getAmphibians()

                AmphibianUiState.Success(listResult)
            } catch (e: IOException) {
                AmphibianUiState.Error
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MarsPhotosApplication)
                val amphibiansPhotoRepository = application.container.amphibianPhotoRepository
                AmphibianViewModel(amphibianPhotosRepository = amphibiansPhotoRepository)
            }
        }
    }

}




