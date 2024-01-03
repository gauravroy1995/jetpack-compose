package com.example.roomwithapi.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomwithapi.container.FlightRepository
import com.example.roomwithapi.database.BusEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShareViewModel(private val busRepository: FlightRepository) : ViewModel() {
    // StateFlow representing all items
    private val _allItems: MutableStateFlow<List<BusEntity>> = MutableStateFlow(emptyList())
    val allItems: StateFlow<List<BusEntity>> = _allItems

    // MutableState for selectedDeparture
    var selectedDeparture by mutableStateOf<BusEntity?>(null)
        private set

    // StateFlow representing filtered items excluding selectedDeparture
    private val _filteredItems: MutableStateFlow<List<BusEntity>> = MutableStateFlow(emptyList())
    val filteredItems: StateFlow<List<BusEntity>> = _filteredItems

    init {
        // Start collecting allItems immediately
        collectAllItems()
    }

    // Function to start collecting allItems
    private fun collectAllItems() {
        viewModelScope.launch {
            busRepository.getAllBusScheduleStream().collect {
                _allItems.value = it
            }
        }
    }

    // Function to update filteredItems based on selectedDeparture
    private fun updateFilteredItems() {
        viewModelScope.launch {
            // Get the current list of all items
            val allItemsList = _allItems.value

            // Log the current list of all items
            Log.d("allItems", "$allItemsList")

            // Filter the list to exclude selectedDeparture
            val filteredList = selectedDeparture?.let { selected ->
                allItemsList.filter { it.id != selected.id }
            } ?: allItemsList

            // Log the filtered list
            Log.d("filteredItems", "$filteredList")

            // Update the filteredItems StateFlow
            _filteredItems.emit(filteredList)
        }
    }

    // Function to set new selectedDeparture and update filteredItems
    fun setNewSelectedDeparture(item: BusEntity) {
        if (item !== null) {
            selectedDeparture = item
            updateFilteredItems()
        }
    }





}