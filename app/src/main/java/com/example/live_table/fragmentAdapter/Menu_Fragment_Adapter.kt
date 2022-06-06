package com.example.live_table.fragmentAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.live_table.R

class Menu_Fragment_Adapter(
    val activity: FragmentActivity?,
    val dish_name: Array<String>,
    val dish_price: Array<String>,
) : RecyclerView.Adapter<Menu_Fragment_Adapter.ViewData>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {

        var view = LayoutInflater.from(activity).inflate(R.layout.menu_item, parent, false)

        return ViewData(view)

    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {

        holder.dish_name_txt.text = dish_name[position]
        holder.dish_price_txt.text = dish_price[position]


    }

    override fun getItemCount(): Int {

        return dish_name.size

    }

    class ViewData(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var dish_name_txt = itemView.findViewById<TextView>(R.id.dish_name_txt)
        var dish_price_txt = itemView.findViewById<TextView>(R.id.dish_price_txt)

    }
}