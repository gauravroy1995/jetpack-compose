package com.example.busschedule.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bus_table")
data class BusEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val stopName: String,
    val arrivalTimeInMillis: Int
)