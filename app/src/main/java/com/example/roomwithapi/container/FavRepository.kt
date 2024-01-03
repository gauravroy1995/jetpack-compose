package com.example.roomwithapi.container


import com.example.roomwithapi.database.FavoritesDao
import com.example.roomwithapi.database.FavoritesEntity
import kotlinx.coroutines.flow.Flow

interface  FavInterface {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllFavorites(): Flow<List<FavoritesEntity>>



    /**
     * Insert item in the data source
     */
    suspend fun insertItem(item: FavoritesEntity)

    /**
     * Delete item from the data source
     */
    suspend fun deleteItem(item: FavoritesEntity)



}


class FavRepository(private val favDao: FavoritesDao) : FavInterface {
    override fun getAllFavorites(): Flow<List<FavoritesEntity>> = favDao.getAllFavorites()

  

    override suspend fun insertItem(item: FavoritesEntity) = favDao.insert(item)

    override suspend fun deleteItem(item: FavoritesEntity) = favDao.delete(item)


}