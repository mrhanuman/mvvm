package com.example.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.model.GroceryItem

class GroceryAdapter(var GroceryList : List<GroceryItem>, private val groceryItemClickListener: GroceryItemClickListener): RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grocery_item,parent,false)
        return GroceryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        holder.itemName.text = GroceryList[position].itemName
        holder.quantity.text = GroceryList[position].itemCount.toString()
        holder.price.text = "Rs. "+GroceryList[position].itemPrice.toString()
        val amount : Int = (GroceryList[position].itemCount * GroceryList[position].itemPrice)
        holder.amount.text = "Rs. $amount"
        holder.dlt.setOnClickListener {
            groceryItemClickListener.OnItemClick(GroceryList[position])
        }

    }

    override fun getItemCount(): Int {
        return GroceryList.size
    }



    class GroceryViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val itemName = itemView.findViewById<TextView>(R.id.name)
        val quantity = itemView.findViewById<TextView>(R.id.quantity)
        val price = itemView.findViewById<TextView>(R.id.price)
        val amount = itemView.findViewById<TextView>(R.id.amt)
        val dlt = itemView.findViewById<ImageView>(R.id.dlt)


    }


    interface GroceryItemClickListener{
        fun OnItemClick(groceryItem: GroceryItem)

    }
}