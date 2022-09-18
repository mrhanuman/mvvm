package com.example.mvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Grocery_items")
data class GroceryItem(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    @ColumnInfo(name = "itemName")
    val itemName : String,
    @ColumnInfo(name = "itemCount")
    val itemCount : Int,
    @ColumnInfo(name = "itemPrice")
    val itemPrice : Int

)


