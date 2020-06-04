package com.unitra.task.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.unitra.task.R
import com.unitra.task.models.ItemModel
import com.unitra.task.room.CustomDAO
import com.unitra.task.room.CustomDatabase
import com.unitra.task.room.CustomEntity

class CustomRepository(applicationContext: Application) : BaseRepository {

    private  var customDao: CustomDAO

    companion object {
        @Volatile private var INSTANCE  : CustomRepository? = null

        fun getInstance(applicationContext : Application) : CustomRepository {
            return INSTANCE ?: CustomRepository(applicationContext)
        }
    }

    init {
        val database = CustomDatabase.getInstance(applicationContext.applicationContext)
        customDao = database!!.customDao()
    }

    //region CRUD Operation
    override fun insert(customEntity: CustomEntity) {

    }

    //endregion

    fun getAll() : LiveData<MutableList<ItemModel>> {
        val localList : LiveData<List<CustomEntity>> = customDao.getAll()
        val requesList : LiveData<MutableList<ItemModel>> =
            Transformations.map(
                localList,
                ::convertList
            )

        return requesList
    }

    private fun convertList(customEntity: List<CustomEntity>) : MutableList<ItemModel> {
        val itemList = mutableListOf<ItemModel>()
        customEntity.map { itemList.add(ItemModel(it.id?:0, it.name?:"",it.lock?:0)) }
        return itemList
    }
}