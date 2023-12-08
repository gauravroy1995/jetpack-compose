package com.example.compose.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [EachBookEntity::class], version = 1, exportSchema = false)
@TypeConverters(TypeConvertersClass::class)
abstract class InventoryDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var Instance: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "book_database")
                    .allowMainThreadQueries()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}