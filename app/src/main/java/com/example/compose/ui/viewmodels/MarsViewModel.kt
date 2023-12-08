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
package com.example.compose.ui.viewmodels

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
import com.example.compose.data.BooksDataRepository
import com.example.compose.data.convertApiToEachBookData
import com.example.compose.database.EachBookEntity
import com.example.compose.network.EachBookClass
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface AmphibianUiState {
    data class Success(val amphibians: List<EachBookEntity>) : AmphibianUiState
    object Error : AmphibianUiState
    object Loading : AmphibianUiState

    object Refreshing : AmphibianUiState
}


class AmphibianViewModel(
    private val amphibianPhotosRepository: AmphibianPhotosRepository,
    private val booksDataRepository: BooksDataRepository
) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var marsUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)
        private set

    private val _booksListInDB = MutableStateFlow<List<EachBookEntity>>(emptyList())
    val booksListInDB: StateFlow<List<EachBookEntity>> get() = _booksListInDB

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        observeBooksListInDB()
//        getAmphibians()
    }

    /**
     * Observes changes in the local database and updates [_booksListInDB].
     */
    private fun observeBooksListInDB() {
        viewModelScope.launch {
            booksDataRepository.getAllItemsStream().collect { items ->
                Log.d("AmphibianViewModel", "ywhyhyh: called $items")
                _booksListInDB.value = items
                marsUiState = AmphibianUiState.Success(items)
                if (items.size == 0) {

                    getAmphibians(0)
                }

            }
        }
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    fun getAmphibians(startIndexForApi:Int) {
        viewModelScope.launch {


            try {
                val listResult = amphibianPhotosRepository.getAmphibians(startIndexForApi)
                Log.d("AmphibianViewModel", "getAmphibians: ${listResult.items.size}")
                if (listResult.items.isNotEmpty()) {

                    for (item in listResult.items) {
                        val eachBook = amphibianPhotosRepository.getEachBook(item.id)

                        val eachBookEntity = convertApiToEachBookData(eachBook)

                        booksDataRepository.insertItem(eachBookEntity)


                    }
                }



            } catch (e: IOException) {
                marsUiState = AmphibianUiState.Error
                Log.e("MarsViewModel", "getAmphibians: $e")
            }
        }
    }

    fun isRefreshing(): Boolean {
        return when (marsUiState) {
            is AmphibianUiState.Refreshing -> true
            else -> false
        }
    }

    fun onLoadMore(){
        getAmphibians(_booksListInDB.value.size)
    }

    fun deleteAll() {
        viewModelScope.launch {
            booksDataRepository.deleteAll()
        }
    }

    fun refetchAmphibians() {
        viewModelScope.launch {
//            marsUiState = AmphibianUiState.Refreshing
            delay(2000)
            marsUiState = AmphibianUiState.Success(_booksListInDB.value)

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MarsPhotosApplication)
                val amphibiansPhotoRepository = application.container.amphibianPhotoRepository
                val booksDataRepository = application.container.booksDataRepository
                AmphibianViewModel(
                    amphibianPhotosRepository = amphibiansPhotoRepository,
                    booksDataRepository = booksDataRepository
                )
            }
        }
    }

}




