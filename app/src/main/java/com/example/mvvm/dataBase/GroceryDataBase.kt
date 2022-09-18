package com.example.mvvm.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm.dao.GroceryDao
import com.example.mvvm.model.GroceryItem

@Database(entities = [GroceryItem::class], version = 1)

abstract class GroceryDataBase : RoomDatabase() {

abstract fun groceryDao():GroceryDao


companion object{
    @Volatile
    private var instance : GroceryDataBase?= null

    fun getDataBase(context: Context): GroceryDataBase {

        if (instance == null) {
            synchronized(this) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    GroceryDataBase::class.java,
                    "contactDB",
                ).build()

            }

        }
        return instance!!
    }


//    private fun createDataBase(context: Context)= Room.databaseBuilder(context.applicationContext,GroceryDataBase::class.java,"Grocery_dataBase").build()


//    private val LOCK = Any()
//
//    operator fun invoke (context: Context) = instance?: synchronized(LOCK){
//        instance?: createDataBase(context).also {
//            instance = it
//        }
//    }
}


}