package com.example.geministarter.repository

import com.example.geministarter.database.PhotoSearchDao
import com.example.geministarter.database.PhotoSearchEntity
import kotlinx.coroutines.flow.Flow


interface FavInterface {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllSearches(): Flow<List<PhotoSearchEntity>>

    /**
     * Insert item in the data source
     */
    suspend fun insertItem(item: PhotoSearchEntity)

    /**
     * Delete item from the data source
     */
    suspend fun deleteItem(item: PhotoSearchEntity)


    /**
     * Update item from the data source
     */
    suspend fun updateItem(item: PhotoSearchEntity)
}

class FavRepository(private val favDao: PhotoSearchDao) : FavInterface {
    override fun getAllSearches(): Flow<List<PhotoSearchEntity>> = favDao.getAllBusSchedule()


    override suspend fun insertItem(item: PhotoSearchEntity) = favDao.insert(item)

    override suspend fun deleteItem(item: PhotoSearchEntity) = favDao.delete(item)

    override suspend fun updateItem(item: PhotoSearchEntity) = favDao.update(item)


}