package com.example.roomwithapi.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airport")
data class BusEntity(
    @PrimaryKey()
    val id: Int,
    val name:String,
    @ColumnInfo(name = "iata_code")
    val iataCode: String,
    val passengers: Long
)