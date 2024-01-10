package com.example.geministarter.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoSearchDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: PhotoSearchEntity)


    @Delete
    suspend fun delete(item: PhotoSearchEntity)

    @Update
    suspend fun update(item: PhotoSearchEntity)


    @Query("SELECT * from photo_search ORDER BY id ASC")
    fun getAllBusSchedule(): Flow<List<PhotoSearchEntity>>


}