package com.example.roomwithapi.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomwithapi.container.FavRepository
import com.example.roomwithapi.database.FavoritesEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(private val favoritesRepository: FavRepository) : ViewModel() {


    private val _favItems: MutableStateFlow<List<FavoritesEntity>> = MutableStateFlow(emptyList())
    val favItems: StateFlow<List<FavoritesEntity>> = _favItems




    init {
        // Start collecting allItems immediately
        collectAllItems()
    }


    // Function to start collecting allItems
    private fun collectAllItems() {
        viewModelScope.launch {
            favoritesRepository.getAllFavorites().collect {
                _favItems.value = it
            }
        }
    }

    suspend fun insertData(item: FavoritesEntity) {
        favoritesRepository.insertItem(item)

    }

    suspend fun deleteData(item: FavoritesEntity) {
        favoritesRepository.deleteItem(item)

    }




    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

