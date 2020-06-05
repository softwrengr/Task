package com.unitra.task.repository

import android.app.Application
import android.os.AsyncTask
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.unitra.task.room.CustomDAO
import com.unitra.task.room.CustomDatabase
import com.unitra.task.room.CustomEntity

class CustomRepository(applicationContext: Application) : BaseRepository {


    private lateinit var customDao : CustomDAO

    companion object {
        @Volatile private var INSTANCE  : CustomRepository? = null

        fun getInstance(applicationContext : Application) : CustomRepository {
            return INSTANCE ?: CustomRepository(applicationContext)
        }
    }

    init {
        val database : CustomDatabase? = CustomDatabase.getInstance(applicationContext.applicationContext)
        customDao = database!!.customDao()
    }

    //region CRUD Operation
    override fun insert(customEntity: CustomEntity) {
        AsyncTask.execute {
            customDao.insert(
                customEntity
            )
        }
    }

    override fun update() {
        AsyncTask.execute {
            customDao.update()

        }
    }


    override fun deleteAll() {
        AsyncTask.execute {
            customDao.deleteAll()
        }
    }

    fun getAll() : LiveData<List<CustomEntity>> {
        return customDao.getAll()
    }
    //endregion
}