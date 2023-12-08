package com.example.compose.database

import androidx.room.Entity
import com.example.compose.network.VolumeInfo

@Entity(tableName = "book_table", primaryKeys = ["id"])
data class EachBookEntity(
    val id: String,
    val title: String,
    val authors: List<String>,
    val publishedDate: String,
    val thumbnail: String,
    val listPrice: Double

)