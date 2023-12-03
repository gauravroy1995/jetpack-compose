package com.example.busschedule.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BusEntity::class], version = 1, exportSchema = false)
abstract class BusDatabase : RoomDatabase() {

    abstract fun busDao(): BusDao

    companion object {
        @Volatile
        private var Instance: BusDatabase? = null

        fun getDatabase(context: Context): BusDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, BusDatabase::class.java, "bus_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}