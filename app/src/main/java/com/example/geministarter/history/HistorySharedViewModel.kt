package com.example.geministarter.history

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.geministarter.database.PhotoSearchEntity

class HistorySharedViewModel() : ViewModel() {
    var selectedHistory  =  mutableStateOf<PhotoSearchEntity?>(null)
        private set

    fun selectHistory(photoSearchEntity: PhotoSearchEntity) {
        selectedHistory.value = photoSearchEntity
    }
}