package com.example.live_table.fragmentAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.live_table.R

class orderAdapter(val factivity: FragmentActivity, val itemName: Array<String>, val itemPrice: Array<String>) : RecyclerView.Adapter<orderAdapter.ViewData>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {

        var view = LayoutInflater.from(factivity).inflate(R.layout.order_dialog_item,parent,false)
        return ViewData(view)

    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {

        holder.itemNameTxt.text = itemName[position]
        holder.itemPriceTxt.text = itemPrice[position]

    }

    override fun getItemCount(): Int {

        return itemName.size

    }

    class ViewData(itemView: View):RecyclerView.ViewHolder(itemView){

        var itemNameTxt = itemView.findViewById<TextView>(R.id.itemName)
        var itemPriceTxt = itemView.findViewById<TextView>(R.id.itemPrice)
        var checkbox = itemView.findViewById<CheckBox>(R.id.checkbox)


    }

}