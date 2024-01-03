package com.example.roomwithapi.container

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.roomwithapi.database.BusDao
import com.example.roomwithapi.database.BusEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

interface  BusInterface {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllBusScheduleStream(): Flow<List<BusEntity>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getBusStream(id: Int): Flow<BusEntity?>

    /**
     * Insert item in the data source
     */
    suspend fun insertItem(item: BusEntity)

    /**
     * Delete item from the data source
     */
    suspend fun deleteItem(item: BusEntity)

    /**
     * Update item in the data source
     */
    suspend fun updateItem(item: BusEntity)

    suspend fun getFlightsFromSearch(searchText: String): Flow<List<BusEntity>>

}


class FlightRepository(private val busDao: BusDao,private val dataStore: DataStore<Preferences>) : BusInterface {
    override fun getAllBusScheduleStream(): Flow<List<BusEntity>> = busDao.getAllBusSchedule()

    override fun getBusStream(id: Int): Flow<BusEntity?> = busDao.getItem(id)

    override suspend fun insertItem(item: BusEntity) = busDao.insert(item)

    override suspend fun deleteItem(item: BusEntity) = busDao.delete(item)

    override suspend fun updateItem(item: BusEntity) = busDao.update(item)

    override suspend fun getFlightsFromSearch(searchText: String): Flow<List<BusEntity>> = busDao.searchByText(searchText)

    private companion object {
        val SEARCH_TEXT_KEY = stringPreferencesKey("search_text_key")
    }

    suspend fun saveSearchTextInDS(searchText: String) {
        dataStore.edit { preferences ->
            preferences[SEARCH_TEXT_KEY] = searchText
        }
    }

    val searchTextDS: Flow<String> = dataStore.data.catch {
        if(it is IOException) {
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map { preferences ->
        preferences[SEARCH_TEXT_KEY] ?: ""
    }



}