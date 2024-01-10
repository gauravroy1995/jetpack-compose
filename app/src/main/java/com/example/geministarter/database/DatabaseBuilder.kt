package com.example.geministarter.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PhotoSearchEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PhotoSearchDatabase : RoomDatabase() {

    abstract fun photoSearchDao(): PhotoSearchDao



    companion object {

        @Volatile
        private var Instance: PhotoSearchDatabase? = null

        fun getDatabase(context: Context): PhotoSearchDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, PhotoSearchDatabase::class.java, "ai_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}