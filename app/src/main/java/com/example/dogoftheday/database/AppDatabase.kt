package com.example.dogoftheday.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


//create db of dog images entities, array of dog images entities
@Database(entities = [DogImageEntity::class], version = 1)

abstract class AppDatabase : RoomDatabase() {
    //need an object to access our functions
    abstract fun dogImageDao(): DogImageDao

    //this object has access to the functionalities above, allows us to access db
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        public fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_datbase"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance

            }
        }
    }
}