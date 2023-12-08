package com.example.compose.data

import com.example.compose.database.BookDao
import com.example.compose.database.EachBookEntity
import kotlinx.coroutines.flow.Flow

class BooksDataRepository(private val bookDao: BookDao) : BooksDataInterface {
    override fun getAllItemsStream(): Flow<List<EachBookEntity>> = bookDao.getAllItems()

    override fun getItemStream(id: Int): Flow<EachBookEntity?> = bookDao.getItem(id)

    override suspend fun insertItem(item: EachBookEntity) = bookDao.insert(item)

    override suspend fun deleteItem(item: EachBookEntity) = bookDao.delete(item)

    override suspend fun updateItem(item: EachBookEntity) = bookDao.update(item)

    override suspend fun deleteAll() = bookDao.deleteAll()
}