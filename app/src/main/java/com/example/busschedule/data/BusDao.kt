package com.example.busschedule.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BusDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: BusEntity)

    @Update
    suspend fun update(item: BusEntity)

    @Delete
    suspend fun delete(item: BusEntity)

    @Query("SELECT * from bus_table WHERE id = :id")
    fun getItem(id: Int): Flow<BusEntity>

    @Query("SELECT * from bus_table ORDER BY arrivalTimeInMillis ASC")
    fun getAllBusSchedule(): Flow<List<BusEntity>>
}