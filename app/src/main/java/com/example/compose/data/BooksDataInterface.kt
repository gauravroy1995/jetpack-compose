package com.example.compose.data

import com.example.compose.database.EachBookEntity
import kotlinx.coroutines.flow.Flow

interface BooksDataInterface {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllItemsStream(): Flow<List<EachBookEntity>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getItemStream(id: Int): Flow<EachBookEntity?>

    /**
     * Insert item in the data source
     */
    suspend fun insertItem(item: EachBookEntity)

    /**
     * Delete item from the data source
     */
    suspend fun deleteItem(item: EachBookEntity)

    /**
     * Update item in the data source
     */
    suspend fun updateItem(item: EachBookEntity)

    /**
     * Delete all items from the data source
     */
    suspend fun deleteAll()
}