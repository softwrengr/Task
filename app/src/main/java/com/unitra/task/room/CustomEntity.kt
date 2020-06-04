package com.unitra.task.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_list")
data class CustomEntity(

    @ColumnInfo(name = "Name")
    var name : String? = null,

    @ColumnInfo(name = "lock")
    var lock : Int? = null
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    var id : Int? = null

}