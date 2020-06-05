package com.unitra.task.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CustomDAO {

    @Insert
    fun insert(customEntity: CustomEntity)

    @Query("UPDATE item_list SET lock = 1  WHERE Name = 'item 2'")
    fun update()

    @Query("DELETE FROM item_list")
    fun deleteAll()

    @Query("SELECT * FROM item_list ORDER BY Id ASC")
    fun getAll() : LiveData<List<CustomEntity>>
}