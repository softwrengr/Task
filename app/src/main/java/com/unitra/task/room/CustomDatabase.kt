package com.unitra.task.room

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [CustomEntity::class],
    version = 2
)
abstract class CustomDatabase : RoomDatabase() {

    abstract fun customDao(): CustomDAO

    companion object {
        @Volatile
        private var instance: CustomDatabase? = null

        fun getInstance(context: Context): CustomDatabase? {
            if (instance == null) {
                synchronized(CustomDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CustomDatabase::class.java, "items_database"
                    )
                        .fallbackToDestructiveMigration()

                        .build()
                }
            }
            return instance
        }
    }
}