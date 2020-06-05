package com.unitra.task.utilities

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.unitra.task.models.ItemModel
import com.unitra.task.room.CustomEntity

class ConvertList {

    companion object {
        private fun toListModel(customEntity: List<CustomEntity>) : MutableList<ItemModel> {
            val itemList : MutableList<ItemModel> = mutableListOf<ItemModel>()
            customEntity.map {
                itemList.add(
                    ItemModel(it.id?:0, it.name?:"",it.lock?:0)
                )
            }
            return itemList
        }

        fun toLiveDataListModel(localList : LiveData<List<CustomEntity>>) : LiveData<MutableList<ItemModel>> {
            return Transformations.map<
                    List<CustomEntity>, //localList Data Type
                    MutableList<ItemModel> //toListModel List Data Type
                    >(
                localList,
                Companion::toListModel
            )
        }

        fun toEntity(ItemModel: ItemModel) : CustomEntity {
            return when(ItemModel.id) {
                null -> {
                    CustomEntity(
                        ItemModel.name?:"",
                        ItemModel.lock?:0
                    )
                }
                else -> {
                    CustomEntity(
                        ItemModel.name?:"",
                        ItemModel.lock?:0
                    )
                }
            }
        }
    }
}