package com.example.geministarter.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geministarter.database.PhotoSearchEntity
import com.example.geministarter.repository.FavRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val busRepository: FavRepository
) : ViewModel() {

    private val _recipesState: MutableStateFlow<List<PhotoSearchEntity>> =
        MutableStateFlow(listOf())
    val recipesState: StateFlow<List<PhotoSearchEntity>> =
        _recipesState.asStateFlow()


    init {
        viewModelScope.launch {
            busRepository.getAllSearches().collect { searches ->
                _recipesState.value = searches
            }
        }

    }

    fun deleteItem(photoSearchEntity: PhotoSearchEntity) {
        viewModelScope.launch {
            busRepository.deleteItem(photoSearchEntity)
        }
    }


}