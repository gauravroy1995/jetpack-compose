package com.example.roomwithapi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [BusEntity::class,FavoritesEntity::class], version = 3, exportSchema = false)
abstract class FlightDatabase : RoomDatabase() {

    abstract fun flightDao(): BusDao
    abstract fun favoritesDao(): FavoritesDao




    companion object {

        private val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Here, you can define the steps for migration from version 1 to version 2.

                // 1. Create the new FavoritesEntity table
                database.execSQL("CREATE TABLE IF NOT EXISTS `favorite` " +
                        "(`id` INTEGER PRIMARY KEY, " +
                        "`name` TEXT, " +
                        "`departure_code` TEXT, " +
                        "`destination_code` TEXT)")

            }
        }


        private val migration_2_3 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Here, you can define the steps for migration from version 1 to version 2.

                // 1. Create the new FavoritesEntity table
                database.execSQL("CREATE TABLE IF NOT EXISTS `favorite` " +
                        "(`id` INTEGER PRIMARY KEY, " +
                        "`departure_code` TEXT, " +
                        "`destination_code` TEXT)")

            }
        }
        @Volatile
        private var Instance: FlightDatabase? = null

        fun getDatabase(context: Context): FlightDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FlightDatabase::class.java, "flight_database")
                    .createFromAsset("database/flight_search.db")
                    .addMigrations(migration_1_2)
                    .addMigrations(migration_2_3)
                    .build()
                    .also { Instance = it }
            }
        }
    }
}