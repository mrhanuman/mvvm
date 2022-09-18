package com.example.mvvm.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvm.model.GroceryItem

@Dao
interface GroceryDao {

    @Insert
    suspend fun insert (item : GroceryItem)

    @Delete
    suspend fun delete (item: GroceryItem)

    @Query("SELECT * From Grocery_items")
    fun getAllItem (): LiveData<List<GroceryItem>>
}