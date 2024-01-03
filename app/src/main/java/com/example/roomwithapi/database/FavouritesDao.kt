package com.example.roomwithapi.database


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: FavoritesEntity)


    @Delete
    suspend fun delete(item: FavoritesEntity)


    @Query("SELECT * from favorite ORDER BY id ASC")
    fun getAllFavorites(): Flow<List<FavoritesEntity>>

}