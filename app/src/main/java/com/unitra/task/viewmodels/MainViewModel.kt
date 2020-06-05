package com.unitra.task.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.unitra.task.utilities.ConvertList
import com.unitra.task.models.ItemModel
import com.unitra.task.repository.CustomRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private  var customRepository : CustomRepository = CustomRepository.getInstance(application)
    private  var liveList : MutableLiveData<MutableList<ItemModel>> = MutableLiveData()
    private  var liveUpdate : MutableLiveData<ItemModel> = MutableLiveData()

    init {
        setItems()
    }


    fun setItems() {
        customRepository.deleteAll()
        customRepository.insert(ConvertList.toEntity(ItemModel(0,"item 1",1)))
        customRepository.insert(ConvertList.toEntity(ItemModel(1,"item 2",0)))
        customRepository.insert(ConvertList.toEntity(ItemModel(2,"item 3",0)))
        customRepository.insert(ConvertList.toEntity(ItemModel(3,"item 4",0)))
        customRepository.insert(ConvertList.toEntity(ItemModel(4,"item 5",0)))
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
        return ConvertList.toLiveDataListModel(
            customRepository.getAll()
        )
    }
}