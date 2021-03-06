package com.unitra.task.callbacks

import com.unitra.task.models.ItemModel

interface ClickListener {

    fun onUpdate(item : ItemModel, position: Int)
}