package com.example.live_table.fragments

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.live_table.R
import com.example.live_table.Utils.DBHelper
import com.example.live_table.Utils.ModelData
import com.example.live_table.fragments.Table_Fragment.Companion.binding_table

class Table_Fragment_Adapter(val factivity: FragmentActivity?, val l1: ArrayList<ModelData>) :
    RecyclerView.Adapter<Table_Fragment_Adapter.ViewData>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        var view = LayoutInflater.from(factivity).inflate(R.layout.item, parent, false)
        return ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.id.text = l1[position].id
        holder.tab_no_txt.text = l1[position].table_no
        holder.customer_name_txt.text = l1[position].customer_name
        holder.num_people_txt.text = l1[position].number_of_people

        holder.main_rele.setOnClickListener {

            opendialog(position)

        }
    }

    override fun getItemCount(): Int {
        return l1.size
    }

    class ViewData(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var id = itemView.findViewById<TextView>(R.id.id)
        var tab_no_txt = itemView.findViewById<TextView>(R.id.tab_no_txt)
        var customer_name_txt = itemView.findViewById<TextView>(R.id.customer_name_txt)
        var num_people_txt = itemView.findViewById<TextView>(R.id.num_people_txt)
        var main_rele = itemView.findViewById<RelativeLayout>(R.id.main_rele)

    }

    fun opendialog(position:Int) {

        var dialog = Dialog(factivity!!)
        dialog.setContentView(R.layout.dialog)
        dialog.show()
        dialog.setCancelable(false)

        var update = dialog.findViewById<Button>(R.id.update_btn)
        var delete = dialog.findViewById<Button>(R.id.delete_btn)
        var cancel = dialog.findViewById<Button>(R.id.cancel_btn)

       update.setOnClickListener {

           var update_dialog = Dialog(factivity)
           update_dialog.setContentView(R.layout.update_dialog)
           update_dialog.show()

           var table_no_edt = update_dialog.findViewById<EditText>(R.id.d_table_no_edt)
           var customer_name_edt = update_dialog.findViewById<EditText>(R.id.customer_name_edt)
           var people_edt = update_dialog.findViewById<EditText>(R.id.people_edt)
           var dialog_update_btn = update_dialog.findViewById<Button>(R.id.dialog_update_btn)

           table_no_edt.setText(""+l1[position].table_no)
           customer_name_edt.setText(""+l1[position].customer_name)
           people_edt.setText(""+l1[position].number_of_people)

           dialog_update_btn.setOnClickListener {


               DBHelper(factivity).updateData(l1[position].id,table_no_edt.text.toString(),customer_name_edt.text.toString(),people_edt.text.toString())
                var l1 = DBHelper(factivity).readData()
               setupRecyclerView(l1)

               Toast.makeText(factivity,"Item Updated Successfully",Toast.LENGTH_LONG).show()

               update_dialog.dismiss()
               dialog.dismiss()
           }
       }

        delete.setOnClickListener {

            DBHelper(factivity).deleteData(l1[position].id)
            var l1 = DBHelper(factivity).readData()
            setupRecyclerView(l1)

            Toast.makeText(factivity,"Item Deleted Successfully",Toast.LENGTH_LONG).show()

            dialog.dismiss()
        }

        cancel.setOnClickListener {

            dialog.dismiss()
        }
    }

    fun setupRecyclerView(l1:ArrayList<ModelData>){

        var adapter = Table_Fragment_Adapter(factivity,l1)
        var layoutManager = LinearLayoutManager(factivity)
        binding_table.seedataRvview.adapter = adapter
        binding_table.seedataRvview.layoutManager = layoutManager

    }
}