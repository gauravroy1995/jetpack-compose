package com.example.busschedule.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.busschedule.container.BusRepository
import com.example.busschedule.data.BusEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val busRepository: BusRepository) : ViewModel() {

    val homeUiState: StateFlow<HomeUiState> =
        busRepository.getAllBusScheduleStream().map { HomeUiState(it) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState()
        )

    suspend fun insertData(item:BusEntity) {
        busRepository.insertItem(item)

    }

    fun getItemById(id: Int):  Flow<BusEntity?> {
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