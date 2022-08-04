package com.example.live_table.activity.historyActivity.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.live_table.R
import com.example.live_table.activity.historyActivity.view.historyActivity
import com.example.live_table.fragmentAdapter.tableFragmentAdapter
import com.example.live_table.utils.DBHelper
import com.example.live_table.utils.ModelData

class historyAdapter(val historyActivity: historyActivity,val list: ArrayList<ModelData>) : RecyclerView.Adapter<ViewData>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        var view =
            LayoutInflater.from(historyActivity).inflate(R.layout.table_details_item, parent, false)
        return ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {

        var db = DBHelper(historyActivity)
        db.readHistoryData()
        holder.tableNoTxt.setText(list[position].tableNoTxt)
        holder.customer_name_txt.setText(list[position].customer_name)
        holder.num_people_txt.setText(list[position].number_of_people)
        holder.time_txt.setText(list[position].booking_time)

        holder.booked_btn.isVisible = false
        holder.editImageBtn.isVisible = false

    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class ViewData(itemView: View) : RecyclerView.ViewHolder(itemView){

    var id = itemView.findViewById<TextView>(R.id.id)
    var tableNoTxt = itemView.findViewById<TextView>(R.id.tableNoTxt)
    var customer_name_txt = itemView.findViewById<TextView>(R.id.customer_name_txt)
    var num_people_txt = itemView.findViewById<TextView>(R.id.num_people_txt)
    var editImageBtn = itemView.findViewById<ImageView>(R.id.editImageBtn)
    var time_txt = itemView.findViewById<TextView>(R.id.time_txt)
    var orderInfo = itemView.findViewById<TextView>(R.id.orderInfo)
    var booked_btn = itemView.findViewById<Button>(R.id.booked_btn)

}
