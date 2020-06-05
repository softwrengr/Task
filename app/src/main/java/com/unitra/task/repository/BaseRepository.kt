package com.unitra.task.repository

import com.unitra.task.room.CustomEntity

interface BaseRepository {

    fun  insert(customEntity: CustomEntity)

    fun  update()


    fun  deleteAll()
}