package com.example.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.database.dao.HustleDao
import com.example.myapplication.database.dao.HustlrDao
import com.example.myapplication.database.model.Hustle
import com.example.myapplication.database.model.Hustlr

@Database(entities = [Hustlr::class, Hustle::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MainDatabase : RoomDatabase() {

    abstract val hustleDao: HustleDao
    abstract val hustlrDao: HustlrDao

    companion object {

        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getInstance(context: Context): MainDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MainDatabase::class.java,
                        "main_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}