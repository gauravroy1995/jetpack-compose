package com.example.busschedule.container

import com.example.busschedule.data.BusEntity
import kotlinx.coroutines.flow.Flow

interface BusInterface {

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
}