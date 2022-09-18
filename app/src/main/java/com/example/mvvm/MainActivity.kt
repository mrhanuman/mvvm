package com.example.mvvm

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.adapter.GroceryAdapter
import com.example.mvvm.dataBase.GroceryDataBase
import com.example.mvvm.model.GroceryItem
import com.example.mvvm.repository.GroceryRepository
import com.example.mvvm.viewModelFactory.ViewModelFactory
import com.example.mvvm.viewmodel.GroceryViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), GroceryAdapter.GroceryItemClickListener {
    lateinit var floatingActionButton: FloatingActionButton
    lateinit var GroceryAdapter: GroceryAdapter
    lateinit var groceryViewModel: GroceryViewModel
    lateinit var list: List<GroceryItem>
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        floatingActionButton = findViewById(R.id.AddBtn)
        recyclerView = findViewById(R.id.recycle)
        recyclerView = findViewById(R.id.recycle)
        list = ArrayList<GroceryItem>()
        val repository = GroceryRepository(GroceryDataBase.getDataBase(this))
        val factory = ViewModelFactory(repository)

        GroceryAdapter = GroceryAdapter(list, this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GroceryAdapter
        groceryViewModel = ViewModelProvider(this, factory)[GroceryViewModel::class.java]
        groceryViewModel.getAllItem().observe(this, Observer {
            GroceryAdapter.GroceryList = it

            GroceryAdapter.notifyDataSetChanged()

        })
        floatingActionButton.setOnClickListener {
            openDialog()
            Log.d("hanu", "onCreate: done")
        }




//        dataBase = GroceryDataBase.getDataBase(this)
//        GlobalScope.launch {
//            dataBase.groceryDao().insert(GroceryItem(0,"apple",2,50)) }
//
//        dataBase.groceryDao().getAllItem().observe(this, Observer {
//            Log.d("Hanuman", it.toString()) })
//
    }

    @SuppressLint("NotifyDataSetChanged")
     fun openDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog)
        val cancelBtn = dialog.findViewById<Button>(R.id.cancelBtn)
        val adBtn = dialog.findViewById<Button>(R.id.adBtn)
        val itemEdit = dialog.findViewById<EditText>(R.id.etName)
        val itemEditQuantity = dialog.findViewById<EditText>(R.id.etQuantity)
        val itemEditPrice = dialog.findViewById<EditText>(R.id.etPrice)

        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }

        adBtn.setOnClickListener {
            val itemName: String = itemEdit.text.toString()
            val itemQuantity: String = itemEditQuantity.text.toString()
            val itemPrice: String = itemEditPrice.text.toString()
            val qty: Int = itemQuantity.toInt()
            val prc: Int = itemPrice.toInt()
            if (itemName.isNotEmpty() && itemQuantity.isNotEmpty() && itemPrice.isNotEmpty()) {
                val items = GroceryItem(0, itemName, qty, prc)
                groceryViewModel.insert(items)
                Toast.makeText(applicationContext, "item added", Toast.LENGTH_SHORT).show()
                GroceryAdapter.notifyDataSetChanged()
                dialog.dismiss()

            } else {
                Toast.makeText(applicationContext, "fill all info !!", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

        }
        dialog.show()

    }

    override fun OnItemClick(groceryItem: GroceryItem) {
        groceryViewModel.delete(groceryItem)
        GroceryAdapter.notifyDataSetChanged()
        Toast.makeText(this, "DELETED SUCCESSFULLY", Toast.LENGTH_SHORT).show()

    }
}