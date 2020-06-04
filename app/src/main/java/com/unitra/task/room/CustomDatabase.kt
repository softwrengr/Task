package com.unitra.task.room

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [CustomEntity::class],
    version = 1
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
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }

        private val roomCallback: Callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance).execute()
            }

        }

        class PopulateDbAsyncTask(db: CustomDatabase?) : AsyncTask<Unit, Unit, Unit>() {
            private val customDAO = db?.customDao()

            override fun doInBackground(vararg param: Unit) {
                customDAO?.insert(CustomEntity("item 0"))
                customDAO?.insert(CustomEntity("item 1"))
                customDAO?.insert(CustomEntity("item 2"))
                customDAO?.insert(CustomEntity("item 3"))
                customDAO?.insert(CustomEntity("item 4"))
            }
        }
    }
}