package com.example.roomwithapi.viewmodel







import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.roomwithapi.FlightApplication



/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel
        initializer {
            HomeViewModel(
                busRepository = busApplication().container.busRepository
            )
        }

    }

    val Factory2 = viewModelFactory {
        // Initializer for ItemEditViewModel
        initializer {
            ShareViewModel(
                busRepository = busApplication().container.busRepository
            )
        }
    }

    val Factory3 = viewModelFactory {
        // Initializer for ItemEditViewModel
        initializer {
            FavoriteViewModel(
                favoritesRepository = busApplication().container.favoritesRepository
            )
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.busApplication(): FlightApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as FlightApplication)
