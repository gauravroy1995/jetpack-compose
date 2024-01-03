package com.example.roomwithapi.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomwithapi.container.FlightRepository
import com.example.roomwithapi.database.BusEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val busRepository: FlightRepository) : ViewModel() {


    var searchText by mutableStateOf("")
        private set

    var searchedList by mutableStateOf<List<BusEntity>>(listOf())
        private set

    val homeUiState: StateFlow<HomeUiState> =
        busRepository.getAllBusScheduleStream().map { HomeUiState(it) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState()
        )




    private fun changeSearchList(list: List<BusEntity>) {
        searchedList = list
    }

    /**
     * Save the searchText in the data store
     */
    fun saveSearchInDs(searchText: String) {
        viewModelScope.launch {
            busRepository.saveSearchTextInDS(searchText)
        }
    }

    /**
     * Get the searchText from the data store
     */
    val searchTextInDs:StateFlow<String> = busRepository.searchTextDS.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        initialValue = ""
    )

    /**
     * Get the searchText from the data store initially
     */
    init {
        viewModelScope.launch {
            searchText = busRepository.searchTextDS.first()
        }
    }


    fun changeSearchText(text: String) {

        searchText = text
    }

    fun performSearch() {
        Log.d("MainActivity", "performSearch: $searchText")
        if (searchText.isNotBlank()) {
            viewModelScope.launch {
                getFlightsFromSearch(searchText).collect { busData ->
                    changeSearchList(busData)
                }

            }
        } else {
            changeSearchList(emptyList())
        }
    }



    suspend fun insertData(item: BusEntity) {
        busRepository.insertItem(item)

    }

    suspend fun getFlightsFromSearch(searchText: String): Flow<List<BusEntity>> {
        return busRepository.getFlightsFromSearch(searchText)
    }

    fun getItemById(id: Int): Flow<BusEntity?> {
        return busRepository.getBusStream(id)
    }


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * Ui State for HomeScreen
 */
data class HomeUiState(val itemList: List<BusEntity> = listOf())

data class FavouritesState(val itemList: List<BusEntity> = listOf())