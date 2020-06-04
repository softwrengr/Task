package com.unitra.task.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.unitra.task.models.ItemModel
import com.unitra.task.repository.CustomRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private  var customRepository : CustomRepository
    private  var liveList : MutableLiveData<MutableList<ItemModel>>

    init {
        customRepository = CustomRepository.getInstance(application)
        liveList = MutableLiveData()
    }


    fun getItems() : LiveData<MutableList<ItemModel>> {
        //return  liveList
        return  customRepository.getAll()
    }

}