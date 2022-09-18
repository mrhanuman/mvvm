package com.example.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.model.GroceryItem
import com.example.mvvm.repository.GroceryRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GroceryViewModel(private val repository: GroceryRepository) : ViewModel() {

    fun insert (item: GroceryItem) = GlobalScope.launch {
        repository.insert(item)
    }
    fun  delete (item: GroceryItem) = GlobalScope.launch {
        repository.delete(item)
    }
    fun getAllItem () : LiveData<List<GroceryItem>>{
        return repository.getAllItem()

    }

}