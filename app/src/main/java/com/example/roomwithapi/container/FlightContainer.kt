package com.example.roomwithapi.container


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.roomwithapi.database.FlightDatabase


interface BusAppContainer {
    val busRepository: FlightRepository
    val favoritesRepository: FavRepository
}

private  val LAYOUT_PREFERENCE_NAME = "layout_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = LAYOUT_PREFERENCE_NAME
)

/**
 * [AppContainer] implementation that provides instance of [BusRepository]
 */
class BusAppDataContainer(private val context: Context) : BusAppContainer {
    /**
     * Implementation for [BusRepository]
     */





    override val busRepository: FlightRepository by lazy {
        FlightRepository(FlightDatabase.getDatabase(context).flightDao(), dataStore = context.dataStore)
    }

    override val favoritesRepository: FavRepository by lazy {
        FavRepository(FlightDatabase.getDatabase(context).favoritesDao())
    }

}