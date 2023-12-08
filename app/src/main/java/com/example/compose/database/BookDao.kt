package com.example.compose.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: EachBookEntity)

    @Update
    suspend fun update(item: EachBookEntity)

    @Delete
    suspend fun delete(item: EachBookEntity)

    @Query("SELECT * from book_table WHERE id = :id")
    fun getItem(id: Int): Flow<EachBookEntity>

    @Query("SELECT * from book_table")
    fun getAllItems(): Flow<List<EachBookEntity>>

    @Query("DELETE FROM book_table")
    fun deleteAll()
}