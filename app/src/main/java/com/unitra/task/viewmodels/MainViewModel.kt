package com.unitra.task.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.unitra.task.utilities.ConvertList
import com.unitra.task.models.ItemModel
import com.unitra.task.repository.CustomRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var customRepository : CustomRepository
    private lateinit var liveList : MutableLiveData<MutableList<ItemModel>>
    private lateinit var liveUpdate : MutableLiveData<ItemModel>

    init {
        customRepository = CustomRepository.getInstance(application)
        liveList = MutableLiveData()
        liveUpdate = MutableLiveData()
        setItems()
    }

  
    @Deprecated("For Static Data")
    fun setItems() {
        customRepository.deleteAll()
        customRepository.insert(ConvertList.toEntity(ItemModel(0,"item 1",1)))
        customRepository.insert(ConvertList.toEntity(ItemModel(1,"item 2",0)))
        customRepository.insert(ConvertList.toEntity(ItemModel(2,"item 3",0)))
        customRepository.insert(ConvertList.toEntity(ItemModel(3,"item 4",0)))
        customRepository.insert(ConvertList.toEntity(ItemModel(4,"item 5",0)))
    }


    fun getUpdate() : LiveData<ItemModel> {
        return liveUpdate
    }

    fun insertItem(item : ItemModel) {
        customRepository.insert(
            ConvertList.toEntity(item)
        )
    }

    fun updateItem(item : ItemModel) {
        liveUpdate.value = item
        liveUpdate.value?.let {
            customRepository.update()
        }
    }


    fun deleteAll() {
        customRepository.deleteAll()
    }

    fun getItems() : LiveData<MutableList<ItemModel>> {
        //return  liveList
        return ConvertList.toLiveDataListModel(
            customRepository.getAll()
        )
    }
}