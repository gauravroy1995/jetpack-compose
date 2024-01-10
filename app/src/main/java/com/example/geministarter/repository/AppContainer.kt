package com.example.geministarter.repository

import android.content.Context
import com.example.geministarter.database.PhotoSearchDatabase


interface BusAppContainer {
    val favoritesRepository: FavRepository
}


/**
 * [AppContainer] implementation that provides instance of [BusRepository]
 */
class BusAppDataContainer(private val context: Context) : BusAppContainer {
    /**
     * Implementation for [BusRepository]
     */


    override val favoritesRepository: FavRepository by lazy {
        FavRepository(PhotoSearchDatabase.getDatabase(context).photoSearchDao())
    }

}