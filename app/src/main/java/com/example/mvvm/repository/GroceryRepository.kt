package com.example.mvvm.repository

import com.example.mvvm.dataBase.GroceryDataBase
import com.example.mvvm.model.GroceryItem

class GroceryRepository(private val dataBase: GroceryDataBase) {
    suspend fun insert (item: GroceryItem) = dataBase.groceryDao().insert(item)
    suspend fun delete (item: GroceryItem) = dataBase.groceryDao().delete(item)
    fun getAllItem() = dataBase.groceryDao().getAllItem()

}