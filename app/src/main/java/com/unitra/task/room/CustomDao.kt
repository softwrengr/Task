package com.unitra.task.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CustomDAO {

    @Insert
    fun insert(customEntity: CustomEntity)

    @Query("SELECT * FROM item_list")
    fun getAll() : LiveData<List<CustomEntity>>
}