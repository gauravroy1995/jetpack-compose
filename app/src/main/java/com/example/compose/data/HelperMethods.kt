package com.example.compose.data

import com.example.compose.database.EachBookEntity
import com.example.compose.network.EachBookClass

fun convertApiToEachBookData(bookApi:EachBookClass): EachBookEntity {
    return EachBookEntity(
        id = bookApi.id,
        title = bookApi.volumeInfo.title,
        authors = bookApi.volumeInfo.authors,
        publishedDate = bookApi.volumeInfo?.publishedDate ?: "Unknown",
        thumbnail = bookApi.volumeInfo.imageLinks?.thumbnail ?: "" ,
        listPrice = bookApi.saleInfo.listPrice?.amount ?: 0.0
    )
}